import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class tests {
    Course c1; Course c2; Course c3; Course c4; Course c5; Course c6; Course c7; Course c8; Course c9;
    Student s1; Student s2; Student s3; Student s4; Student s5; Student s6; Student s7; Student s8; Student s12;

    @BeforeEach
    void setUp() {

        //These are all great classes I would recommend to anyone :)

        this.c1 = new Course("RUSS 224",3);
        this.c2 = new Course("COMP 230");
        this.c3 = new Course("COMP 302");
        this.c4 = new Course("MATH 235", 4);
        this.c5 = new Course("PHIL 242", 5);
        this.c6 = new Course("COMP 250", 4);
        this.c7 = new Course("Math 338",4);
        this.c8 = new Course("PHYS 224",3);
        this.c9 = new Course("SOCI 377",2);
        this.s1 = new Student(1,"Phoebe");
        this.s2 = new Student(2,"Toledo");
        this.s3 = new Student(3,"Silberman");
        this.s4 = new Student(4,"Ponyo");
        this.s5 = new Student(5,"Adrianne");
        this.s6 = new Student(6,"Dorian");
        this.s7 = new Student(7,"Keeley");
        this.s8 = new Student(8,"Fonda");
        this.s12 = new Student(12,"Fleabag");
    }

    //Test 1: Adding and removing from a course
    @Test
    @DisplayName("Nikola K. Test 1")
    void testConfirmAddRemove() {
        assertEquals(0, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertTrue(this.c9.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertTrue(this.c9.put(s2));
        assertEquals(2, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertTrue(this.c9.put(s3));
        assertEquals(2, this.c9.getCourseSize());
        assertEquals(1, this.c9.waitlist.size());
        assertEquals(s3, this.c9.waitlist.get(0));

        assertTrue(this.c9.put(s12));
        assertEquals(3, this.c9.getCourseSize());
        assertEquals(1, this.c9.waitlist.size());
        assertEquals(s12, this.c9.waitlist.get(0));

        assertTrue(this.c9.put(s5));
        assertEquals(4, this.c9.getCourseSize());
        assertEquals(1, this.c9.waitlist.size());
        assertEquals(s5, this.c9.waitlist.get(0));

        assertTrue(this.c9.put(s4));
        assertEquals(4, this.c9.getCourseSize());
        assertEquals(2, this.c9.waitlist.size());
        assertEquals(s5, this.c9.waitlist.get(0));
        assertEquals(s4, this.c9.waitlist.get(1));

        assertTrue(this.c9.put(s7));
        assertEquals(6, this.c9.getCourseSize());
        assertEquals(1, this.c9.waitlist.size());
        assertEquals(s7, this.c9.waitlist.get(0));

        assertEquals(1, s1.courseCodes.size());
        assertEquals(1, s2.courseCodes.size());
        assertEquals(1, s3.courseCodes.size());
        assertEquals(1, s12.courseCodes.size());
        assertEquals(1, s5.courseCodes.size());
        assertEquals(1, s4.courseCodes.size());
        assertEquals(1, s7.courseCodes.size());
    }

    //Test 2: Student is only limited to 3 courses
    @Test
    @DisplayName("Nikola K. Test 2")
    void testConfirmCourseLimit() {
        assertEquals(0, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(0, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(0, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertTrue(this.c9.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(0, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(0, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertTrue(this.c1.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(1, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(0, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertTrue(this.c2.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(1, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(1, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertFalse(this.c2.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(1, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(1, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertFalse(this.c4.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(1, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(1, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertEquals(0, this.c4.getCourseSize());
        assertEquals(0, this.c4.waitlist.size());
    }

    //Test 3:
    //Make sure that course removal leads to reduction in student courseCodes size
    @Test
    @DisplayName("Nikola K. Test 3")
    void testRemoval() {
        assertEquals(0, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(0, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(0, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertEquals(0, s1.courseCodes.size());

        assertTrue(this.c9.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(0, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(0, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertEquals(1, s1.courseCodes.size());

        assertTrue(this.c1.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(1, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(0, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertEquals(2, s1.courseCodes.size());

        assertTrue(this.c2.put(s1));
        assertEquals(1, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());

        assertEquals(1, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(1, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertEquals(3, s1.courseCodes.size());

        assertEquals(s1, this.c9.remove(s1.id));

        assertEquals(0, this.c9.getCourseSize());
        assertEquals(0, this.c9.waitlist.size());
        assertEquals(2, s1.courseCodes.size());

        assertEquals(1, this.c1.getCourseSize());
        assertEquals(0, this.c1.waitlist.size());

        assertEquals(1, this.c2.getCourseSize());
        assertEquals(0, this.c2.waitlist.size());

        assertEquals(null, this.c9.remove(s1.id));
    }

    //Test 4: Change Array Length test
    @Test
    @DisplayName("Nikola K. Test 4")
    void testARL() {
        Student[] s_list = new Student[20];
        for (int i = 0; i < 20; i++) {
            s_list[i] = new Student((2*i)+1, "Student "+i);
            assertTrue(this.c4.put(s_list[i]));
        }

        for (Student i: s_list) {
            assertEquals(1, i.courseCodes.size());
        }
        assertEquals(s_list[s_list.length-1], this.c4.remove((19*2)+1));
        assertEquals(19, this.c4.getCourseSize());
        assertEquals(0, this.c4.waitlist.size());

        this.c4.changeArrayLength(2);
        assertEquals(19, this.c4.studentTable[1].size());
        assertEquals(null, this.c4.studentTable[0]);

        this.c4.changeArrayLength(3);
        assertEquals(6, this.c4.studentTable[2].size());
        assertEquals(7, this.c4.studentTable[1].size());
        assertEquals(6, this.c4.studentTable[0].size());

        this.c4.changeArrayLength(4);
        assertEquals(9, this.c4.studentTable[3].size());
        assertEquals(null, this.c4.studentTable[2]);
        assertEquals(10, this.c4.studentTable[1].size());
        assertEquals(null, this.c4.studentTable[0]);
    }

    //Test 5: Waitlists are equivalent to registrations
    @Test
    @DisplayName("Nikola K. Test 5")
    void testWaitlisting() {
        assertEquals(0, this.c1.getCourseSize());
        assertEquals(0, this.c8.getCourseSize());
        assertEquals(0, this.c9.getCourseSize());

        assertTrue(this.c1.put(s1));
        assertTrue(this.c1.put(s2));
        assertTrue(this.c1.put(s3));
        assertEquals(3, this.c1.getCourseSize());

        assertTrue(this.c8.put(s1));
        assertTrue(this.c8.put(s2));
        assertTrue(this.c8.put(s3));
        assertEquals(3, this.c8.getCourseSize());

        assertTrue(this.c4.put(s1));
        assertTrue(this.c4.put(s2));
        assertTrue(this.c4.put(s3));
        assertTrue(this.c4.put(s5));
        assertEquals(4, this.c4.getCourseSize());

        assertTrue(this.c1.put(s4));
        assertTrue(this.c8.put(s4));
        assertTrue(this.c4.put(s4));

        assertEquals(3, s4.courseCodes.size());
        assertFalse(this.c1.put(s4));
        assertFalse(this.c5.put(s4));
    }

    //From discussion board
    @Test
    @DisplayName("Ed Test 1 - Made by Chloe Seljak [Modified by Nikola K.]")
    void testEd1() {
        assertEquals(0, this.c6.size);

        this.c6.put(s1);
        this.c6.put(s2);
        this.c6.put(s3);
        this.c6.put(s4);
        assertEquals(4, this.c6.size);
        assertEquals(4, this.c6.capacity);

        this.c6.put(s5);
        this.c6.put(s6);
        assertEquals(4, this.c6.size);
        assertEquals(2, this.c6.waitlist.size());
        assertEquals(4, this.c6.capacity);

        this.c6.put(s7);
        assertEquals(6, this.c6.capacity);
        assertEquals(6, this.c6.size);
        assertEquals(1, this.c6.waitlist.size());
    }

    @Test
    @DisplayName("Ed Test 2 - Made by David Ly")
    void testEd2() {
        this.c9.put(s1); //c9 capacity  = 2
        assertEquals(1, this.c9.size);
        assertEquals(2, this.c9.capacity);
        assertEquals(0, this.c9.waitlist.size());
        this.c9.put(s2); // size is at capacity
        assertEquals(2, this.c9.size);
        assertEquals(2, this.c9.capacity);
        assertEquals(0, this.c9.waitlist.size());
        this.c9.put(s3); // size at capacity + 1 waitlist
        assertEquals(2, this.c9.size);
        assertEquals(2, this.c9.capacity);
        assertEquals(1, this.c9.waitlist.size());
        this.c9.put(s4); // size at capacity + 1 waitlist
        assertEquals(3, this.c9.capacity);
        assertEquals(3, this.c9.size);
        assertEquals(1, this.c9.waitlist.size());
        this.c9.put(s5);
        assertEquals(4, this.c9.size);
        assertEquals(4, this.c9.capacity);
        assertEquals(1, this.c9.waitlist.size());
        this.c9.put(s6);
        assertEquals(4, this.c9.size);
        assertEquals(4, this.c9.capacity);
        assertEquals(2, this.c9.waitlist.size());
        this.c9.put(s7);
        assertEquals(6, this.c9.size);
        assertEquals(6, this.c9.capacity);
        assertEquals(1, this.c9.waitlist.size());
        this.c9.put(s8);
        assertEquals(6, this.c9.size);
        assertEquals(6, this.c9.capacity);
        assertEquals(2, this.c9.waitlist.size());
    }

    @Test
    @DisplayName("Ed Test 3 - Made by Ziyue Wang")
    void testEd3() {
        c1.put(s1);
        c2.put(s1);
        c3.put(s1);
        assertFalse(c1.put(s1));
        assertEquals(1,c1.getCourseSize());
        assertEquals(1,c2.getCourseSize());
        assertEquals(1,c3.getCourseSize());
        assertEquals(3,s1.courseCodes.size());
        c2.remove(s1.id);
        assertEquals(2,s1.courseCodes.size());
        assertEquals(1,c1.getCourseSize());
        assertEquals(0,c2.getCourseSize());
        assertEquals(1,c3.getCourseSize());

        c1.put(s2);
        c1.put(s3);
        c1.put(s4);
        assertEquals(3,c1.size);
        assertEquals(s4,c1.waitlist.getFirst());
        assertEquals(1,s4.courseCodes.size());  // assume put in waitlist also give coursecode
        c1.remove(s4.id);
        assertEquals(0,c1.waitlist.size());
        assertEquals(0,s4.courseCodes.size());
    }

    @Test
    @DisplayName("Ed Test 4 - Made by Ziyue Wang")
    void testEd4() {
        this.c7.put(s1);
        this.c7.put(s2);
        this.c7.put(s3);
        this.c7.put(s4);
        this.c7.put(s5);
        this.c7.put(s6);
        assertEquals(s1, this.c7.remove(1));
        assertEquals(4, this.c7.size);
        assertNull(s1.courseCodes.getFirst());
        assertEquals(1,c7.waitlist.size());
    }

    @Test
    @DisplayName("Ed Test 5 - Made by Shuzhao Feng [Modified by Nikola K.]")
    void testEd5() {
        assertEquals(0, this.c2.size);
        assertEquals(0, this.c2.waitlist.size());

        Student[] s_list = new Student[10];

        for (int i = 0; i < this.c2.capacity; i++){ // map all available seats with students
            Student s = new Student((261000000+i),"RegisteredStudent".concat(String.valueOf(i)));
            assertTrue(this.c2.put(s));
            s_list[i] = s;
            if (i == 0 || i == this.c2.capacity - 1){ // just arbitrary values
                assertFalse(this.c2.put(s)); // this checks for redundancy
            }
        }
        assertEquals(10, this.c2.size);
        assertEquals(10, this.c2.capacity);
        assertEquals(0, this.c2.waitlist.size());
        assertEquals(1, s_list[0].courseCodes.size());
        assertEquals(1, s_list[9].courseCodes.size());

        Student[] s_list2 = new Student[5];
        for (int i = 0; i < (int)(this.c2.capacity/2); i++){
            Student s = new Student((261000100+i),"waitlistStudent".concat(String.valueOf(i)));
            assertTrue(this.c2.put(s));
            s_list2[i] = s;
            if (i == 0){ // just an arbitrary value
                assertFalse(this.c2.put(s)); // check for waitlist redundancy
            }
        }
        assertEquals(10, this.c2.size);
        assertEquals(10, this.c2.capacity);
        assertEquals(5, this.c2.waitlist.size());
        assertEquals(1, s_list2[4].courseCodes.size());

        Student t_pet = new Student(261000200,"The teacher's pet");
        assertTrue(this.c2.put(t_pet)); // put one more student in
        assertEquals(15, this.c2.size);
        assertEquals(15, this.c2.capacity);
        assertEquals(1, this.c2.waitlist.size());
        assertEquals(t_pet, this.c2.waitlist.getFirst());

        assertEquals(t_pet, this.c2.remove(261000200));
        assertEquals(null, this.c2.get(261000200));
        assertEquals(0, t_pet.courseCodes.size());
        assertEquals(15, this.c2.size);
        assertEquals(15, this.c2.capacity);
        assertEquals(0, this.c2.waitlist.size());

        assertEquals(s_list[0], this.c2.get(261000000));

        Student stu = new Student(261000300,"The one no one remembers :-(");
        assertEquals(0, stu.courseCodes.size());
        assertTrue(this.c1.put(stu));
        assertTrue(this.c3.put(stu));
        assertTrue(this.c4.put(stu));
        assertFalse(this.c2.put(stu));
        assertFalse(stu.isRegisteredOrWaitlisted(c2.code));
        assertEquals(null, this.c2.get(stu.id));
    }

    @Test
    @DisplayName("Ed Test 6 - Made by Matt Chaubet")
    void testEd6() {
        //c9 capacity = 2
        c9.put(s1);
        c9.put(s2);
        assertEquals(null, c9.remove(0));
        assertEquals(2, c9.getCourseSize());
        assertEquals(2, c9.capacity);

        assertEquals(s2, c9.remove(2));
        assertEquals(null, c9.remove(2));
        assertEquals(1, c9.getCourseSize());
        assertEquals(2, c9.capacity);
        c9.put(s2);
        c9.put(s3);//course full + waitlist full
        c9.put(s4);//capacity -> 3, waitlist.size() -> 1
        assertEquals(3, c9.capacity);
        assertEquals(3, c9.getCourseSize());
        assertEquals(1, c9.waitlist.size());
        assertEquals(s4, c9.remove(4));
        assertEquals(0, c9.waitlist.size());
    }
}
