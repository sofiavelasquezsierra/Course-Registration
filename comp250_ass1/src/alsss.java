public class alsss {

    public static void main(String[] args) {
       Course math240 = new Course("math240");
       Student MR = new Student(1, "Mateo Ronflard");
       Student SV = new Student(2, "Sofia Velasquez");
       Student NC = new Student (3, "Natacha Carasso");

      math240.put(MR);
      math240.put(MR);
      math240.put(SV);
      math240.put(NC);
      math240.get(6);
        System.out.println(math240);
      math240.remove(SV.id);

        System.out.println(math240);

        math240.getRegisteredIDs();
        math240.getCourseSize();
        math240.getWaitlistedIDs();
        math240.getRegisteredStudents();
        math240.getWaitlistedStudents();

    }
}
