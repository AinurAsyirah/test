package com.heroku.java.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.java.MODEL.student_register_model;

import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.sql.Connection;
//import java.sql.ResultSet;
// import java.util.ArrayList;
// import java.util.Map;
import java.sql.SQLException;

// import org.jscience.physics.amount.Amount;
// import org.jscience.physics.model.RelativisticModel;
// import javax.measure.unit.SI;
@SpringBootApplication
@Controller
public class student_register {
    private final DataSource dataSource;

    @Autowired
    public student_register(DataSource dataSource) {
        this.dataSource = dataSource;
    }

@PostMapping("/signup")
public String registerAcc(HttpSession session, @ModelAttribute("signup") student_register_model stud){
    try{
        Connection con=dataSource.getConnection();
        final var s=con.prepareStatement("INSERT INTO student(studentIC,studentName,studentEmail,studentPhone,studentDOB,studentGender,studentClass,studentPassword,studentAddress,studentForm) VALUES (?,?,?,?,?,?,?,?,?,?)");
    
       
        String studIC= stud.getUsername();
        String name= stud.getNama();
        String email=stud.getEmail();
        String phone=stud.getNotel();
        String dob=stud.getDob();
        String gender=stud.getJantina();
        String form=stud.getTingkatan();
        String kelas=stud.getKelas();
        String address=stud.getAlamat();
        String password =stud.getPassword();

        s.setString(1,studIC);
        s.setString(2,name);
        s.setString(3,email);
        s.setString(4,phone);
        s.setString(5,dob);
        s.setString(6,gender);
        s.setString(7,form);
        s.setString(8,kelas);
        s.setString(9,address);
        s.setString(10,password);
        s.executeUpdate();

        con.close();
        return "redirect:/signin";
    }catch (SQLException sqe) {
        System.out.println("Error Code = " + sqe.getErrorCode());
        System.out.println("SQL state = " + sqe.getSQLState());
        System.out.println("Message = " + sqe.getMessage());
        System.out.println("printTrace /n");
        sqe.printStackTrace();
  
        return "redirect:/signup";
      } catch (Exception e) {
        System.out.println("E message : " + e.getMessage());
        return "redirect:/signup";
      }

}
}
