package hw15.q1.entities;



public  class Person {

    private String firstname;
    private String lastName;
    private String sex;
    private Integer age;

    public Person(String firstname, String lastName, String sex, Integer age) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
