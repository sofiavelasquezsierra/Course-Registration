/*
@author Sofia Velasquez Sierra
 */

import java.util.LinkedList;

public class Course {
    public String code;
    public int capacity;
    public SLinkedList<Student>[] studentTable;
    public int size;
    public SLinkedList<Student> waitlist;


    public Course(String code) {
        this.code = code;
        this.studentTable = new SLinkedList[10];
        this.size = 0;
        this.waitlist = new SLinkedList<Student>();
        this.capacity = 10;
    }

    public Course(String code, int capacity) {
        this.code = code;
        this.studentTable = new SLinkedList[capacity];
        this.size = 0;
        this.waitlist = new SLinkedList<>();
        this.capacity = capacity;
    }

    public void changeArrayLength(int m) {

        SLinkedList<Student>[] changedTable = new SLinkedList[m];

        for(int i = 0; i < studentTable.length; i++){
            if (studentTable[i] != null){
              for (int j = 0; j < studentTable[i].size(); j++) {
                Student s = studentTable[i].get(j);
                if (changedTable[s.id % m] != null){
                    changedTable[s.id % m].addFirst(s);
                }
                else{
                    changedTable[s.id % m] = new SLinkedList<>();
                    changedTable[s.id % m].addFirst(s);
                }
              }
            }
        }

        studentTable = changedTable;
    }

    public boolean put(Student s) {
        // insert your solution here and modify the return statement
        Student newStudent = s;
        int Slotindex = newStudent.id % this.capacity;

        if (studentTable[Slotindex] == null) {
            this.studentTable[Slotindex] = new SLinkedList<Student>();
        }
        if (studentTable[Slotindex].getIndexOf(newStudent) >= 0) {
            return false;
        } else if (this.waitlist.getIndexOf(newStudent) >= 0) {
            return false;
        } else if (newStudent.courseCodes.size() == 3) {
            return false;

        } else {

            if (this.size < capacity) {

            this.studentTable[Slotindex].addLast(newStudent);
            newStudent.addCourse(code);
            this.size++;
            return true;
        } else if (this.size >= capacity) {

            if (this.waitlist.size() < (int) (0.5 * capacity)) {
                waitlist.addLast(s);
                s.addCourse(this.code);
            }
            else{
                changeArrayLength((int)(1.5*capacity));
                this.capacity = (int)(1.5*capacity);
                for (int i = 0; i < waitlist.size(); i++){
                    Student z = waitlist.get(i);
                    if (studentTable[z.id % capacity] != null){
                        studentTable[z.id % capacity].addFirst(z);
                    }
                    else{
                        studentTable[z.id % capacity] = new SLinkedList<>();
                        studentTable[z.id % capacity].addFirst(z);
                    }
                    size ++;
                }
                waitlist.clear();
                waitlist.addFirst(newStudent);
                newStudent.addCourse(code);
                return true;
            }

        }
        }
        return true;
    }


    public Student get(int id) {
        // insert your solution here and modify the return statement
        int Slotindex = id % this.capacity;

        if (studentTable[Slotindex] != null){
            for (int i = 0; i < studentTable[Slotindex].size(); i++){
                //studentTable[Slotindex].get(i);
                if (studentTable[Slotindex].get(i).id == id) {
                    return studentTable[Slotindex].get(i);
                }
            }
        }

        if (waitlist != null){
            for (int i = 0; i < waitlist.size(); i++){
                if (waitlist.get(i).id == id){
                    return waitlist.get(i);
                }
            }
        }
        return null;
    }


    public Student remove (int id) {
     //   insert your solution here and modify the return statement
        int Slotindex = id % this.capacity;

        // iterate studentTable
        if (studentTable[Slotindex] != null){
            for (int i = 0; i < studentTable[Slotindex].size(); i++){
                if(studentTable[Slotindex].get(i).id == id) {
                    this.size--;
                    //remove(id);
                   if (waitlist.size() > 0) {
                       put(waitlist.removeFirst());
                   }
                    studentTable[Slotindex].get(i).dropCourse(code);
                    return studentTable[Slotindex].remove(i);

                }
            }
        }

        //iterate waitlist
        if (waitlist != null){
            for (int i = 0; i < waitlist.size(); i ++){
                if (waitlist.get(i).id == id){
                    Student s = waitlist.remove(i);
                    s.dropCourse(code);
                    return s;
                }
            }
        }
        return null;
    }


    public int getCourseSize() {
       return this.size;
    }


    public int[] getRegisteredIDs() {
        // insert your solution here and modify the return statement
        int[] registeredids = new int[size];
        int count = 0;

        for (int i = 0; i < capacity; i++){
            int Slotindex = i % this.capacity;
            if (studentTable[Slotindex] != null){
                for (int j = 0; j < studentTable[Slotindex].size(); j ++) {
                    registeredids[count] = studentTable[Slotindex].get(j).id;
                    count ++;
                }
            }
        }
        return registeredids;
    }


    public Student[] getRegisteredStudents() {
        // insert your solution here and modify the return statement
        Student[] registeredstudents = new Student[size];
        int count = 0;

        for (int i = 0; i < capacity; i++) {
            int Slotindex = i % this.capacity;
            if (studentTable[Slotindex] != null) {
                for (int j = 0; j < studentTable[Slotindex].size(); j++) {
                    registeredstudents[count] = studentTable[Slotindex].get(j);
                    count++;
                }
            }
        }
        return registeredstudents;
    }

    public int[] getWaitlistedIDs() {
        // insert your solution here and modify the return statement
        int[] waitlistedids = new int[waitlist.size()];
        int count = 0;

        if (waitlist != null){
            for (int j = 0; j < waitlist.size(); j ++) {
                waitlistedids[count] = waitlist.get(j).id;
                count++;
            }
        }
        return waitlistedids;
    }


    public Student[] getWaitlistedStudents() {
        // insert your solution here and modify the return statement
        Student[] waitlistedstudents = new Student[waitlist.size()];
        int count = 0;

        if (waitlist != null) {
                for (int j = 0; j < waitlist.size(); j++) {
                    waitlistedstudents[count] = waitlist.get(j);
                    count++;
                }
        }
        return waitlistedstudents;
    }


    public String toString() {
        String s = "Course: "+ this.code +"\n";
        s += "--------\n";
        for (int i = 0; i < this.studentTable.length; i++) {
            s += "|"+i+"     |\n";
            s += "|  ------> ";
            SLinkedList<Student> list = this.studentTable[i];
            if (list != null) {
                for (int j = 0; j < list.size(); j++) {
                    Student student = list.get(j);
                    s +=  student.id + ": "+ student.name +" --> ";
                }
            }
            s += "\n--------\n\n";
        }

        return s;
    }
}
