package com.lavanya;

public class Modal {
     public int orderId;
     public String name;
     public  String contact;
     public  String size;
     public  int toppings;
     public  double total;

     public Modal(int orderId, String name, String contact, String size, int toppings, double total) {
          this.orderId = orderId;
          this.name = name;
          this.contact = contact;
          this.size = size;
          this.toppings = toppings;
          this.total = total;
     }

     public int getOrderId() {
          return orderId;
     }

     public void setOrderId(int orderId) {
          this.orderId = orderId;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getContact() {
          return contact;
     }

     public void setContact(String contact) {
          this.contact = contact;
     }

     public String getSize() {
          return size;
     }

     public void setSize(String size) {
          this.size = size;
     }

     public int getToppings() {
          return toppings;
     }

     public void setToppings(int toppings) {
          this.toppings = toppings;
     }

     public double getTotal() {
          return total;
     }

     public void setTotal(double total) {
          this.total = total;
     }


}
