import java.util.ArrayList;
import java.util.List;

// Adapter Pattern implementation 
// Use case: Adapting a legacy payment system to work with a new e-commerce platform 

// Legacy payment system
class LegacyPaymentSystem{
    public void processPayment(double amount){
        System.out.println("Processing payment of $" + amount + " using legacy system ...");
    }
}

// New payment interface required by the e-commerce platform 
interface INewPaymentSystem{
    public void makePayment(double amount, String currency);
}

// Adapter to make LegacyPaymentSystem compatible with INewPaymentSystem
class PaymentSystemAdapter implements INewPaymentSystem{
    private LegacyPaymentSystem legacySystem;

    public PaymentSystemAdapter(LegacyPaymentSystem legacySystem){
        this.legacySystem = legacySystem;
    }

    @Override
    public void makePayment(double amount, String currency) {
        if (!currency.equals("USD")){
            throw new IllegalArgumentException("Legacy System only supports USD");
        }
        legacySystem.processPayment(amount);
    }
}

// Composite Pattern implementation
// Use case: Representing a file system with directories and files

abstract class FileSystemItem{
    protected String name;

    public abstract int getSize();

    public String getName(){
        return name;
    }
}

class File extends FileSystemItem{
    private int size;

    public File(String name, int size){
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}

class Directory extends FileSystemItem{
    private List<FileSystemItem> items = new ArrayList<>();

    public Directory(String name){
        this.name  = name;
    }

    public void addItem(FileSystemItem item){
        items.add(item);
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemItem item : items){
            totalSize += item.getSize();
        }
        return totalSize;
    }
}
public class Structural {
    public static void main(String[] args) {
        // Adapter Pattern Demo
        System.out.println("Adapter Pattern Demo:");
        LegacyPaymentSystem legacySystem = new LegacyPaymentSystem();
        INewPaymentSystem adapter = new PaymentSystemAdapter(legacySystem);
        adapter.makePayment(100.00, "USD");
        System.out.println();

        // Composite Pattern Demo
        System.out.println("Composite Pattern Demo:");
        Directory root = new Directory("root");
        Directory documents = new Directory("documents");
        File file1 = new File("file1.txt", 100);
        File file2 = new File("file2.txt", 200);

        root.addItem(documents);
        root.addItem(file1);
        documents.addItem(file2);

        System.out.println("Size of root: " + root.getSize() + " bytes");
        System.out.println("Size of documents: " + documents.getSize() + " bytes");
    }
}
