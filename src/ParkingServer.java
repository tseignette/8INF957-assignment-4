import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import com.sun.net.httpserver.Headers;

public class ParkingServer {

  // ===============================================================================================
  // PRIVATE ATTRIBUTES
  // ===============================================================================================
  ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();


  // ===============================================================================================
  // PRIVATE METHODS
  // ===============================================================================================
  public void getAll(HttpExchange exchange) throws IOException {
    String response = parkingSpots.toString();

    exchange.sendResponseHeaders(200, response.length());
    OutputStream output = exchange.getResponseBody();
    output.write(response.getBytes());
    output.close();
  }

  public void getFree(HttpExchange exchange) throws IOException {
    ArrayList<ParkingSpot> free = new ArrayList<ParkingSpot>();
    
    for (ParkingSpot spot : parkingSpots) {
      if (spot.getState() == ParkingSpot.FREE)
        free.add(spot);
    }

    String response = free.toString();

    exchange.sendResponseHeaders(200, response.length());
    OutputStream output = exchange.getResponseBody();
    output.write(response.getBytes());
    output.close();
  }


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public void addParkingSpot(int id) {
    this.parkingSpots.add(new ParkingSpot(id));
  }

  public void updateParkingSpot(int id, Character state) {
    for (ParkingSpot spot : this.parkingSpots) {
      if (spot.getId() == id) {
        spot.setState(ParkingSpot.parseSensorState(state));
        return;
      }
    }
  }

  public void start(int port) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/all", new HttpHandler(){
      public void handle(HttpExchange h) throws IOException { getAll(h); }
    });
    server.createContext("/free", new HttpHandler(){
      public void handle(HttpExchange h) throws IOException { getFree(h); }
    });
    server.start();
  }


}