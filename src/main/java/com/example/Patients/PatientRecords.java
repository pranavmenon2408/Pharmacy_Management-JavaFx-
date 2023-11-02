package com.example.Patients;

public class PatientRecords {
    private String name;
    private String address;
    private String mPhoneNo;
    private String mConcernedDepartment;
    private int age;
    private boolean checkup;
    private boolean surgery;
    public PatientRecords(){}
    public PatientRecords(String name,String address,String pno,String cd,int age,boolean checkup,boolean surgery){
        this.name=name;
        this.address=address;
        mPhoneNo=pno;
        mConcernedDepartment=cd;
        this.age=age;
        this.checkup=checkup;
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
    public boolean getCheckup(){
        return checkup;
    }
    public void setCheckup(boolean checkup){
        this.checkup=checkup;
    }
    public boolean getSurgery(){
        return surgery;
    }
    public void setSurgery(boolean surgery){
        this.surgery=surgery;
    }

    @Override
    public String toString(){
        return "Name is "+ name+"\nAddress is "+address+"\nPhone number is "+mPhoneNo+"\nAge is "+age+"\nConcerned Department is "+mConcernedDepartment+"\nSurgery: "+surgery+"\nCheckup: "+checkup;
    }
}
