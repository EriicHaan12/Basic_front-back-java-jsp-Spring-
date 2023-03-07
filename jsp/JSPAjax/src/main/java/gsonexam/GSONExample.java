	package gsonexam;
	
	import java.io.IOException;
	import java.lang.reflect.Type;
	import java.sql.Date;
	import java.util.ArrayList;
	import java.util.Collection;
	import java.util.List;
	
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import com.erichan.vo.Employees;
	import com.google.gson.Gson;
	import com.google.gson.reflect.TypeToken;
	
	/**
	 * Servlet implementation class GSONExample
	 */
	@WebServlet("/GSONExample")
	public class GSONExample extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	   
	    public GSONExample() {
	        super();
	      
	    }
	
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
	Employees emp = new Employees(1000, "길동", "홍", "hkd@abc.com","010-1234-5678", 
			new Date(2023,3,7), "IT_PROG", 5000,0.0f,200 , 90, "Executive");
		System.out.println(emp.toString());	
		
		Employees emp2 = new Employees(1001, "찰찰", "홍", "hchch@abc.com","010-1234-5679", 
				new Date(2023,3,7), "IT_PROG", 5000,0.0f,200 , 90, "Executive");
			System.out.println(emp.toString());			
		
		Gson gson = new Gson();
		
		String json =gson.toJson(emp, Employees.class); // 일반객체-> json
		System.out.println(json);
		
		List<Employees> lst = new ArrayList<>();
		lst.add(emp); 
		lst.add(emp2);
		
	//	Type typeOfSrc = new TypeToken<Collection<Foo>>() {}.getType();
		//TypeToken : List 컬렉션에 들어있는 Employees 각 객체를 분리하는 객체
		Type type = new TypeToken<List<Employees>>() {}.getType();
		
		String json2 = gson.toJson(lst, type); //컬랙션 객체 -> json
		
		System.out.println(json2);
			 }
		}
