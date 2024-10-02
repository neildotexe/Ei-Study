// Singleton Pattern

class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;

    private DatabaseConnection(){
        // Private constructor to prevent direct instantiation
        connectionString = "jdbc:mysql://localhost:3306/mydb";
    }

    public static synchronized DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect(){
        System.out.println("Connecting to database: " + connectionString);
    }
}

// Factory Method Pattern

// Product interface
interface Vehicle{
    void drive();
}

//Concrete products
class car implements Vehicle{
    @Override
    public void drive(){
        System.out.println("Driving a car");
    }
}

class Motorcycle implements Vehicle{
    @Override
    public void drive(){
        System.out.println("Riding a motorcycle");
    }
}

class Truck implements Vehicle{
    @Override
    public void drive(){
        System.out.println("Driving a truck");
    }
}

// Creator abstract class 
abstract class VehicleFactory{
    public abstract Vehicle createVehicle();

    public void deliverVehicle(){
        Vehicle vehicle = createVehicle();
        System.out.println("Delivering the vehicle: ");
        vehicle.drive();
    }
}

//Concrete creators
class CarFactory extends VehicleFactory{
    @Override
    public Vehicle createVehicle(){
        return new car();
    }   
}

class MotorcycleFactory extends VehicleFactory{
    @Override
    public Vehicle createVehicle(){
        return new Motorcycle();
    }   
}

class TruckFactory extends VehicleFactory{
    @Override
    public Vehicle createVehicle(){
        return new Truck();
    }   
}

public class Creational {
    public static void main(String[] args){
        //Singleton Pattern demo
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        System.out.println("Are db1 and db2 the same instance? " + (db1 == db2));
        db1.connect();

        //Factory Method Pattern demo
        VehicleFactory CarFactory = new CarFactory();
        VehicleFactory MotorcycleFactory = new MotorcycleFactory();
        VehicleFactory TruckFactory = new TruckFactory();

        CarFactory.deliverVehicle();
        MotorcycleFactory.deliverVehicle();
        TruckFactory.deliverVehicle();
    }
}