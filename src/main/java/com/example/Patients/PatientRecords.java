package com.example.Patients;

public class PatientRecords {
    private String name;
    private String address;
    private String mPhoneNo;
    private String email;
    private String mConcernedDepartment;
    private int age;
    private boolean illness;
    private boolean injury;
    private boolean surgery;
    public PatientRecords(){}
    public PatientRecords(String name,String address,String email,String pno,String cd,int age,boolean illness,boolean injury,boolean surgery){
        this.name=name;
        this.address=address;
        this.email=email;
        mPhoneNo=pno;
        mConcernedDepartment=cd;
        this.age=age;
        this.illness=illness;
        this.injury=injury;
        this.surgery=surgery;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
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
    public String getConcernedDepartment(){
        return mConcernedDepartment;
    }
    public void setConcernedDepartment(String cd){
        mConcernedDepartment=cd;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public boolean getIllness(){
        return illness;
    }
    public void setIllness(boolean illness){
        this.illness=illness;
    }
    public boolean getInjury(){
        return injury;
    }
    public void setInjury(boolean injury){
        this.injury=injury;
    }
    public boolean getSurgery(){
        return surgery;
    }
    public void setSurgery(boolean surgery){
        this.surgery=surgery;
    }

    @Override
    public String toString(){
        return "Name is "+ name+"\nAddress is "+address+"\nPhone number is "+mPhoneNo+"\nAge is "+age+"\nConcerned Department is "+mConcernedDepartment;
    }
}
