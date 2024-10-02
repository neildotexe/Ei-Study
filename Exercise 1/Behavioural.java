// Observer Pattern 

import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface Subject{
    void registerOberver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

interface Observer{
    void update(String message);
}

class NewsAgency implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private String news;

    @Override
    public void registerOberver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(news);
        }
    }   

    public void setNews(String news){
        this.news = news;
        notifyObservers();
    }
}

// Concrete Observer 
class NewsChannel implements Observer{
    private String name;

    public NewsChannel(String name){
        this.name = name;
    }
    @Override   
    public void update(String message) {
        System.out.println(name + " received news: " + message);
    }
}

// Strategy Pattern
interface PaymentMethod{
    void pay(int amount);
}

class CreditCardPayment implements PaymentMethod{
    private String name;
    private String cardNumber;

    public CreditCardPayment(String name, String cardNumber){
        this.name = name;
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println(name + " paid " + amount + " using credit card ending with " + cardNumber.substring(cardNumber.length() - 4));
    }
}

class UpiPayment implements PaymentMethod{
    private String name;
    private String upi_id;

    public UpiPayment(String name, String upi_id){
        this.name = name;
        this.upi_id = upi_id;
    }

    @Override
    public void pay(int amount) {
        System.out.println(name + " paid " + amount + " using UPI id " + upi_id);
    }
}

//Context 
class ShoppingCart{
    private PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public void checkout(int amount){
        paymentMethod.pay(amount);
    }
}

public class Behavioural{
    public static void main(String[] args) {
        //Observer Pattern Demo
        NewsAgency agency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel("Channel 1");
        NewsChannel channel2 = new NewsChannel("Channel 2");

        agency.registerOberver(channel1);
        agency.registerOberver(channel2);

        agency.setNews("Breaking News !!!");
        
        //Strategy Pattern Demo
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentMethod(new CreditCardPayment("Neil", "1234567890123456"));
        cart.checkout(1000);

        cart.setPaymentMethod(new UpiPayment("mahua", "1234567890@ybl"));
        cart.checkout(250);
    }
}