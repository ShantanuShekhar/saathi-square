//package com.saathisquare.societyservice.model;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.UUID;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class RevenueLog {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "entry_id")
//    private UUID entryId;
//    @Column(name = "society_id")
//    private UUID societyId;
//    @Column(name = "amount_recieved")
//    private Double amountReceived;
//    @Column(name = "plan_type")
//    private String planType;
//    @Column(name = "duration")
//    private String duration;	
//    @Column(name = "payment_date")
//    private LocalDateTime paymentDate = LocalDateTime.now();
//    // Getters and Setters
//}
