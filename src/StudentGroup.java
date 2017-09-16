import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		// Add your implementation here
		return this.students;
	}

	@Override
	public void setStudents(Student[] students){
		// Add your implementation here
		if( students == null ){
			throw new IllegalArgumentException();
		}
		this.students = students;
	}

	@Override
	public Student getStudent(int index) {
		if( index < 0 || index >= this.students.length ){
			throw new IllegalArgumentException();
		}
		return this.students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		if( students == null || index < 0 || index >= this.students.length ){
			throw new IllegalArgumentException();
		}
		this.students[index] = student;
	}

	@Override
	public void addFirst(Student student) {
		if( students == null ){
			throw new IllegalArgumentException();
		}
		this.add(student, 0);
	}

	@Override
	public void addLast(Student student) {
		if( students == null ){
			throw new IllegalArgumentException();
		}
		this.add(student, this.students.length );
	}

	@Override
	public void add(Student student, int index) {
		if( students == null || index < 0 || index > this.students.length ){
			throw new IllegalArgumentException();
		}		
		List<Student> list = new ArrayList<Student>();
		for( int i=0 ; i< this.students.length ; i++ ){
			if( i == index ){
				list.add( student );
			}
			list.add( this.students[i] );
		}
		this.setStudents( (Student[])list.toArray() );
	}

	@Override
	public void remove(int index) {
		if(index < 0 || index > this.students.length ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( i != index ){
				list.add( this.students[i] );
			}
		}
		this.setStudents( (Student[])list.toArray() );	
	}

	@Override
	public void remove(Student student) {
		if( student == null ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( !this.students[i].equals(student) ){
				list.add( this.students[i] );
			}
		}
		this.setStudents( (Student[])list.toArray() );
	}

	@Override
	public void removeFromIndex(int index) {
		if( index < 0 || index > this.students.length ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( i>index ){
				break;
			}
			list.add( this.students[i] );
		}
		this.setStudents( (Student[])list.toArray() );
	}

	@Override
	public void removeFromElement(Student student) {
		if( student==null ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( this.students[i].equals(student)  ){
				list.add( this.students[i] );
				break;
			}
			list.add( this.students[i] );
		}
		this.setStudents( (Student[])list.toArray() );
	}

	@Override
	public void removeToIndex(int index) {
		if( index < 0 || index > this.students.length ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( i<=index ){
				list.add( this.students[i] );
			}else{
				break;
			}
		}
		this.setStudents( (Student[])list.toArray() );
	}

	@Override
	public void removeToElement(Student student) {
		if( student==null ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		boolean reachedElement = false;
		for(int i=0;i<this.students.length;i++){
			if( this.students[i].equals(student)  ){
				reachedElement = true;
				list.add( this.students[i] );
				continue;
			}
			if( reachedElement ){
				list.add( this.students[i] );
			}	
		}
		this.setStudents( (Student[])list.toArray() );
	}

	@Override
	public void bubbleSort() {
        int n = this.students.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (this.students[j].getId() > this.students[j+1].getId() )
                {
                    Student temp = this.students[j];
                    this.students[j] = this.students[j+1];
                    this.students[j+1] = temp;
                }
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		if( date == null ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( this.students[i].getBirthDate().compareTo(date) <= 0){
				list.add( this.students[i] );
			}
		}
		return (Student[])list.toArray();
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if( firstDate == null || lastDate == null ){
			throw new IllegalArgumentException();
		}
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( this.students[i].getBirthDate().compareTo(firstDate) >= 0 && this.students[i].getBirthDate().compareTo(lastDate) <= 0 ){
				list.add( this.students[i] );
			}
		}
		return (Student[])list.toArray();
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		if( date == null ){
			throw new IllegalArgumentException();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( this.students[i].getBirthDate().compareTo(date) > 0 && this.students[i].getBirthDate().compareTo(cal.getTime()) < 0){
				list.add( this.students[i] );
			}
		}
		return (Student[])list.toArray();
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		if( indexOfStudent < 0 || indexOfStudent > this.students.length ){
			throw new IllegalArgumentException();
		}
		Date now = new Date();
		Date dob = this.students[ indexOfStudent ].getBirthDate();
		int currentyear = now.getYear();
		int birthYear = dob.getYear();
		int yeardiff = currentyear - birthYear;
		int currentMonth = now.getMonth();
		int birthMonth = dob.getMonth();
		int currentDate = now.getDay();
		int birthDate = now.getDay();
		if( currentMonth < birthMonth ){
			yeardiff = yeardiff-1;
		}else if( currentMonth == birthMonth && birthDate > currentDate ){
			yeardiff = yeardiff-1;
		}
		return yeardiff;
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		List<Student> list = new ArrayList<Student>();
		for(int i=0;i<this.students.length;i++){
			if( this.getCurrentAgeByDate(i) == age ){
				list.add( this.students[i]);
			}
		}
		return (Student[])list.toArray();
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		double max = 0;
		for( int i=0 ; i< this.students.length ; i++ ){		
			if( this.students[i].getAvgMark() == max ){
				max = this.students[i].getAvgMark();
			}
		}
		List<Student> list = new ArrayList<Student>();
		for( int i=0 ; i< this.students.length ; i++ ){		
			if( this.students[i].getAvgMark() == max ){
				list.add( this.students[i] );
			}
		}
		return (Student[])list.toArray();
	}

	@Override
	public Student getNextStudent(Student student) {
		if( student == null ){
			throw new IllegalArgumentException();
		}
		boolean gotIt = false;
		for( int i=0 ; i< this.students.length ; i++ ){		
			if( gotIt ){
				return this.students[i];
			}
			if( this.students[i].equals(student) ){
				gotIt = true;
			}
		}
		return null;
	}
}
