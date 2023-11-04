package com.example.Employees;

import java.util.List;

public class EmployeeRecord {
    private String name;
    private int age;
    private String department;
    private String email;
    private String address;
    private String mPhoneNo;
    private int mEmpId;
    private List<String> assignments;
    public EmployeeRecord(){}
    public EmployeeRecord(String name,int age,String dept,String email,String address,String pno,int empid,List<String> assignments){
        this.name=name;
        this.age=age;
        department=dept;
        this.email=email;
        this.address=address;
        mPhoneNo=pno;
        mEmpId=empid;
        this.assignments=assignments;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String dept){
        department=dept;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPhoneNo(){
        return mPhoneNo;
    }
    public void setPhoneNo(String pno){
        mPhoneNo=pno;
    }
    public int getEmpId(){
        return mEmpId;
    }
    public void setEmpId(int empid){
        mEmpId=empid;
    }
    public List<String> getAssignments(){
        return assignments;
    }
    public void setAssignments(List<String> assignments){
        this.assignments=assignments;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    @Override
    public String toString(){
        return "Name: "+name+"\t\t\tAge: "+age+"\nDepartment: "+department+"\t\tEmail: "+email+
        "\nPhone No: "+mPhoneNo+"\t\tAddress: "+ address+"\nEmpId: "+mEmpId;
    }

    
}
