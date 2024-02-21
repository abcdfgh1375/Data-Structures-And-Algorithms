
package Tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class Flight implements Comparable<Flight> {

    String flightNumber;
    String takeoffTime;
    String destination;
    String status;

    Flight() {
    }

    public Flight(String flightNumber, String takeoffTime, String destination, String status) {
        this.flightNumber = flightNumber;
        this.takeoffTime = takeoffTime;
        this.destination = destination;
        this.status = status;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getTakeoffTime() {
        return takeoffTime;
    }

    public void setTakeoffTime(String takeoffTime) {
        this.takeoffTime = takeoffTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Flight{" + "flightNumber=" + flightNumber + ", takeoffTime=" + takeoffTime + ", destination=" + destination + ", status=" + status + '}';
    }
    
    @Override
    public int compareTo(Flight other) {
    if (this.status.equals("Scheduled") && !other.status.equals("Scheduled")) {
        return -1; 
    } else if (!this.status.equals("Scheduled") && other.status.equals("Scheduled")) {
        return 1; 
    } else {
        return this.takeoffTime.compareTo(other.takeoffTime);
    }
}

    
    public void loadFromFile(List<Flight> bookingRequest, String fileName) throws NumberFormatException {
        try {
            BuildHeap b = new BuildHeap();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            if ((line = br.readLine()) == null) {
                System.out.println("File is empty");
                return;
            } else {
                do {
//                    System.out.println(line);
                    String[] linearr = line.split("\\|");
                    try {
                        Flight a = new Flight(linearr[0],linearr[1] ,linearr[2] ,linearr[3] );
                        b.insert(bookingRequest, a);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing float at line: " + line);
                    }
                } while ((line = br.readLine()) != null);
            }
            br.close();
        } catch (FileNotFoundException nfe) {
            System.out.println("File not found " + fileName);
        } catch (IOException ex) {
            System.out.println("An error occurred while reading the file");
        }
        System.out.println("Load from file successfulled!");
    }
}
