package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeapForFlight {

    public static void main(String[] args) {
//        String a = "Not Scheduled";
//        System.out.println(a.compareTo("Scheduled"));
        Scanner sc = new Scanner(System.in);
        BuildHeap heap = new BuildHeap();
        List<Flight> bookingRequest = new ArrayList<>();
        Flight flight = new Flight();
        flight.loadFromFile(bookingRequest, "FlightBR.txt");
        String msg = """
                           1.Build Heap
                           2.Update Status Flight
                           3.Delete Flight
                           4.Print Flight Book Request
                           5.Exit""";
        int choice;
        do {
            System.out.println(msg);
            System.out.println("Enter selection:");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    heap.buildHeap(bookingRequest, bookingRequest.size());
                    heap.insert(bookingRequest, new Flight("AG203",null,null,null));
                    heap.printHeap(bookingRequest, bookingRequest.size());
                }
                case 2 -> {
                    sc.nextLine();
                    System.out.println("Enter code:");
                    String codeFlight = sc.nextLine();
                    System.out.println("Enter new status: ");
                    String status = sc.nextLine();
                    heap.updateFlight(bookingRequest, codeFlight, status);

                    heap.printHeap(bookingRequest, bookingRequest.size());
                }
                case 3 -> {
                    sc.nextLine();
                    System.out.println("Enter code:");
                    String codeFlight = sc.nextLine();
                    heap.deleteFlight(bookingRequest, heap.searchFlight(bookingRequest, codeFlight));

                    heap.printHeap(bookingRequest, bookingRequest.size());
                }
                case 4 -> {
                    heap.printBookRequest(bookingRequest);
                }
                case 5 -> {
                    System.exit(0);
                }default->{
                    System.exit(0);
                }
            }
        } while (choice < 5);
        heap.updateFlight(bookingRequest, "AA101", "Not Scheduled");
        heap.printHeap(bookingRequest, bookingRequest.size());

    }
}

class BuildHeap {

    public void swap(List<Flight> bookingRequest, int index1, int index2) {
        Flight temp = bookingRequest.get(index1);
        bookingRequest.set(index1, bookingRequest.get(index2));
        bookingRequest.set(index2, temp);
    }

    public void heapifyMax(List<Flight> bookingRequest, int size, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < size && bookingRequest.get(l).compareTo(bookingRequest.get(largest)) > 0) {
            largest = l;
        }
        if (r < size && bookingRequest.get(r).compareTo(bookingRequest.get(largest)) > 0) {
            largest = r;
        }
        if (largest != i) {
            swap(bookingRequest, i, largest);
            heapifyMax(bookingRequest, size, largest);
            //heapify cho các node lá phía sau
        }
    }

    public void heapifyMin(List<Flight> bookingRequest, int size, int i) {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < size && bookingRequest.get(l).compareTo(bookingRequest.get(smallest)) < 0) {
            smallest = l;
        }
        if (r < size && bookingRequest.get(r).compareTo(bookingRequest.get(smallest)) < 0) {
            smallest = r;
        }
        if (smallest != i) {
            swap(bookingRequest, i, smallest);
            heapifyMin(bookingRequest, size, smallest);
            // heapify cho các node lá phía sau
        }
    }

    public void buildHeap(List<Flight> bookingRequest, int size) {
        int stIndex = (size / 2) - 1;
        for (int i = stIndex; i >= 0; i--) {
            heapifyMin(bookingRequest, size, i);
            //heapify cho các node phía trên node bắt đầu
        }
    }
//INSERT 

    public void insert(List<Flight> bookingRequest, Flight newFlight) {
        int size = bookingRequest.size();
        if (size == 0) {
            bookingRequest.add(newFlight);
        } else {
            bookingRequest.add(newFlight);
            for (int i = 0; i < size / 2; i++) {
                heapifyMin(bookingRequest, size, i);
            }
        }
    }
//UPDATE STATUS

    public boolean updateFlight(List<Flight> bookingRequest, String flightNumber, String newStatus) {
        for (Flight flight : bookingRequest) {
            if (flight.flightNumber.equals(flightNumber)) {
                flight.setStatus(newStatus);
                buildHeap(bookingRequest, bookingRequest.size());
                return true;
            }
        }
        return false;
    }
//DELETE FLIGHT

    public Flight searchFlight(List<Flight> bookingRequest, String flightNumber) {
        for (Flight flight : bookingRequest) {
            if (flight.flightNumber.equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public boolean deleteFlight(List<Flight> bookingRequest, Flight flight) {
        int index = bookingRequest.indexOf(flight);
        if (index == -1) {
            return false;
        }
        swap(bookingRequest, index, bookingRequest.size() - 1);
        bookingRequest.remove(bookingRequest.size() - 1);
        buildHeap(bookingRequest, bookingRequest.size());
        return true;
    }

//PRINT
    void printHeap(List<Flight> bookingRequest, int size) {
        System.out.println("Array representation of Heap is:");
        for (int i = 0; i < size; ++i) {
            System.out.print(bookingRequest.get(i).flightNumber + " ");
        }
        System.out.println("\n");
    }

    void printBookRequest(List<Flight> br) {
        for (Flight f : br) {
            System.out.println(String.format("%-10s%-10s%-10s%s", f.getFlightNumber(),
                    f.getTakeoffTime(), f.getDestination(), f.getStatus()));
        }
    }

}
