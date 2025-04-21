package Kuliah.Semester2;
// !!!
// IMPORTANT NOTE: Kerjakan dan fokus ke bagian kode yang ada keterangan TODO saja.
// DILARANG MENGUBAH KODE SELAIN BAGIAN KODE YANG DIMINTA UNTUK DIKERJAKAN!!!
// KETAHUAN MENGUBAH KODE -> KEMUNGKINAN PAKAI CHATGPT -> NILAI 0 UNTUK SOAL TERSEBUT!!!
// !!!


import java.util.*;
import java.io.*;

public class Solution {
	static abstract class Vehicle {
        String plate;
        String inDate;  
        int inHour;
        int inMinute;
        boolean vip;
        
        public Vehicle(String plate, String inDate, int inHour, int inMinute, boolean vip) {
            this.plate = plate;
            this.inDate = inDate;
            this.inHour = inHour;
            this.inMinute = inMinute;
            this.vip = vip;
        }
        
        public abstract int getBaseRate(Set<String> holidays);
        public abstract int getDailyCap();
        public abstract String getType();
    }
  
  	
  	static class Car extends Vehicle {
        public Car(String plate, String inDate, int inHour, int inMinute, boolean vip) {
            super(plate, inDate, inHour, inMinute, vip);
        }
        
        public int getBaseRate(Set<String> holidays) {
            if (holidays.contains(inDate)) {
                return 3500;
            }
            return 3000;
        }
        
        public int getDailyCap() {
            return 50000;
        }
        
        public String getType() {
            return "Car";
        }
    }
  
  	
  	static class Motorcycle extends Vehicle {
        public Motorcycle(String plate, String inDate, int inHour, int inMinute, boolean vip) {
            super(plate, inDate, inHour, inMinute, vip);
        }
        
        public int getBaseRate(Set<String> holidays) {
            if (holidays.contains(inDate)) {
                return 3000;
            }
            return 2700;
        }
        
        public int getDailyCap() {
            return 30000;
        }
        
        public String getType() {
            return "Motorcycle";
        }
    }
  
  
  	static class Ticket {
        int id;
        int slot;
        Vehicle vehicle;
        
        Ticket(int id, int slot, Vehicle vehicle) {
            this.id = id;
            this.slot = slot;
            this.vehicle = vehicle;
        }
        
        public String formatTicket() {
            return "Ticket #" + id + " | Plate: " + vehicle.plate + " | Slot: " + slot + " | In: " + vehicle.inDate;
        }
    }
  
  	
  	static class ParkingLot {
        int totalSlots;
        TreeSet<Integer> availableSlots;
        Map<String, Ticket> parked;
        int ticketCounter;
        
        ParkingLot(int totalSlots) {
            this.totalSlots = totalSlots;
            availableSlots = new TreeSet<Integer>();
            for (int i = 1; i <= totalSlots; i++) {
                availableSlots.add(i);
            }
            parked = new HashMap<String, Ticket>();
            ticketCounter = 1;
        }
        
        public String park(Vehicle vehicle, Set<String> holidays) {
            if (availableSlots.isEmpty()) {
                return "Parking Lot Full";
            }
            int slot = availableSlots.pollFirst(); 			   // TODO: Pilih slot yang ingin diisi menggunakan attribute yang sudah ada
          
            Ticket ticket = new Ticket(ticketCounter++, slot, vehicle);
            parked.put(vehicle.plate, ticket);
            return "Parked at slot " + slot + " | Ticket: " + ticket.formatTicket();
        }
      
      
      	public String leave(String plate, String outDate, int outHour, int outMinute, Set<String> holidays) {
            if (!parked.containsKey(plate)) {
                return "Vehicle not found";
            }
            Ticket ticket = parked.get(plate);
            int totalMinutes = diffInMinutes(ticket.vehicle.inDate, ticket.vehicle.inHour, ticket.vehicle.inMinute, outDate, outHour, outMinute);
            int baseRate = ticket.vehicle.getBaseRate(holidays);          // TODO: Panggil baseRate dari method class tertentu 
            int dailyCap = ticket.vehicle.getDailyCap();          // TODO: Panggil dailyCap dari method class tertentu
            boolean isWeekend = isWeekend(outDate); // TODO: Tentukan apakah isWeekend menggunakan method yang sudah ada
            int fee = calculateFee(totalMinutes, baseRate, dailyCap); 		       // TODO: Hitung fee berdasarkan method yang sudah ada
            
            if(isWeekend) {
                fee = (int)Math.round(fee * 1.2);
            }
            if(ticket.vehicle.vip && ticket.vehicle.getType().equalsIgnoreCase("Car")) {
                fee = (int)Math.round(fee * 0.9);
            }
            availableSlots.add(ticket.slot);
            parked.remove(plate);
            return "Charge: " + fee;
        }
      
      	public String status() {
            StringBuilder sb = new StringBuilder();
            sb.append("Slot | Plate | Type | In Time");
            List<Ticket> tickets = new ArrayList<Ticket>(parked.values());
            Collections.sort(tickets, new Comparator<Ticket>() {
                public int compare(Ticket t1, Ticket t2) {
                    return t1.slot - t2.slot;
                }
            });
            for (Ticket t : tickets) {
                sb.append("\n" + t.slot + " | " + t.vehicle.plate + " | " + t.vehicle.getType() + " | " + t.vehicle.inDate);
            }
            return sb.toString();
        }
    }
  
  	static int diffInMinutes(String inDate, int inHour, int inMinute, String outDate, int outHour, int outMinute) {
        int inDays = dateToDays(inDate);
        int outDays = dateToDays(outDate);
        int totalInMinutes = inDays * 1440 + inHour * 60 + inMinute;
        int totalOutMinutes = outDays * 1440 + outHour * 60 + outMinute;
        return totalOutMinutes - totalInMinutes;
    }
    
    static int dateToDays(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        int days = year * 365 + countLeapYears(year, month);
        days += dayOfYear(year, month, day);
        return days;
    }
    
    static int countLeapYears(int year, int month) {
        if(month <= 2) {
            year--;
        }
        return year / 4 - year / 100 + year / 400;
    }
  
  
  	static int dayOfYear(int year, int month, int day) {
        int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (isLeapYear(year)) {
            monthDays[1] = 29;
        }
        int dayCount = 0;
        for (int i = 0; i < month - 1; i++) {
            dayCount += monthDays[i];
        }
        dayCount += day;
        return dayCount;
    }
    
    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
  
  	
  	// TODO: Selesaikan implementasi perhitungan harga parkir
    // Aturan: 30 menit pertama gratis, pembulatan ke atas tiap blok 15 menit,
    // tarif progresif (misalnya blok 1-2 = baseRate, blok 3-4 = baseRate*1.5, blok >=5 = baseRate*2)
    // serta penerapan daily cap.
    static int calculateFee(int totalMinutes, int baseRate, int dailyCap) {
        if(totalMinutes<=30){
            return 0;
        }
        int remainMinutes = totalMinutes-30;
        int blok = (int) Math.ceil((double) remainMinutes/15);
        double fee = 0;
        
        if (blok >= 1 && blok <= 2) fee = blok * baseRate;
        else if (blok >= 3 && blok <= 4) fee = (2 * baseRate) + ((blok - 2) * baseRate * 1.5);
        else if (blok >= 5) fee = (2 * baseRate) + (2 * baseRate * 1.5) + ((blok - 4) * baseRate * 2);
        return Math.min((int) Math.round(fee), dailyCap);
    }
  
  	static int dayOfWeek(String date) {
        String[] parts = date.split("-");
        int y = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int d = Integer.parseInt(parts[2]);
        if (m < 3) {
            m += 12;
            y -= 1;
        }
        int k = y % 100;
        int j = y / 100;
        int h = (d + (13 * (m + 1)) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;
        return h;
    }
    
    static boolean isWeekend(String date) {
        int h = dayOfWeek(date);
        return (h == 0 || h == 1);
    }
	
  	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int slotCount = Integer.parseInt(sc.nextLine());
        int holidayCount = Integer.parseInt(sc.nextLine());
        Set<String> holidays = new HashSet<>();
        for (int i = 0; i < holidayCount; i++) holidays.add(sc.nextLine());

        int commandCount = Integer.parseInt(sc.nextLine());
        ParkingLot lot = new ParkingLot(slotCount);

        for (int i = 0; i < commandCount; i++) {
            String[] parts = sc.nextLine().split(" ");
            String cmd = parts[0];

            if (cmd.equals("PARK")) {
                // TODO: Ambil input dan buat objek kendaraan
              	// Format: PARK <Type> <Plate> <YYYY-MM-DD> <HH:MM> <isVip>
                String type = parts[1];
                String plate = parts[2];
                String inDate = parts[3];
                String[] timeParts = parts[4].split(":");
                int inHour = Integer.parseInt(timeParts[0]);
                int inMinute = Integer.parseInt(timeParts[1]);
                boolean isVip = Boolean.parseBoolean(parts[5]);

                Vehicle vehicle = null;
                if (type.equalsIgnoreCase("Car")) {
                    vehicle = new Car(plate, inDate, inHour, inMinute, isVip);
                } else if (type.equalsIgnoreCase("Motorcycle")) {
                    vehicle = new Motorcycle(plate, inDate, inHour, inMinute, isVip);
                }
                if (vehicle != null) {
                    System.out.println(lot.park(vehicle, holidays));
                } else {
                    System.out.println("Invalid vehicle type");
                }
              
            } else if (cmd.equals("LEAVE")) {
                // TODO: Ambil input dan proses leave
              	// Format: LEAVE <Plate> <YYYY-MM-DD> <HH:MM>
                String plate = parts[1];
                String outDate = parts[2];
                String[] timeParts = parts[3].split(":");
                int outHour = Integer.parseInt(timeParts[0]);
                int outMinute = Integer.parseInt(timeParts[1]);
                System.out.println(lot.leave(plate, outDate, outHour, outMinute, holidays));
              
            } else if (cmd.equals("STATUS")) {
                // TODO: Tampilkan status menggunakan System.out
                System.out.println(lot.status());
            }
        }
        sc.close();
    }

}

