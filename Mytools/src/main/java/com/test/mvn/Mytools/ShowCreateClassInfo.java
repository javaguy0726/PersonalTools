package com.test.mvn.Mytools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ShowCreateClassInfo {

	private static ShowCreateClassInfo instance;
	private String className;
	
	private ShowCreateClassInfo(String name) {
		this.className=name;
	}

	public static ShowCreateClassInfo getInstance(String name) {
		if (instance == null) {
			instance = new ShowCreateClassInfo(name);
		}
		return instance;
	}

	public String showClassDeclaration() {
		Class<?> cls = getClass(this.className);

		String class_declaration = "";
		String modifiers = Modifier.toString(cls.getModifiers());
		String name = typeName(cls);
		String super_class_name=typeName(cls.getSuperclass());
		
		if (cls.isInterface()) { // if is interface, then the "interface" will be included in the modifiers.
			class_declaration = modifiers + " " + name;
		} else if (cls.getSuperclass() != null) {
			class_declaration = modifiers +" class "+ name+" extends " + super_class_name;
		} else {
			class_declaration = modifiers +" class "+ name;
		}
		
		Class<?>[] interfaces = cls.getInterfaces(); //if class, then get interfaces implemented; if interface, then get interfaces extended
		if ((interfaces != null) && (interfaces.length > 0)) {
			if (cls.isInterface())
				class_declaration+=" extends ";
			else
				class_declaration+=" implements ";
			for (Class<?> inter_face : interfaces) {
				String interface_name=typeName(inter_face);
				if (inter_face==interfaces[0]){
					class_declaration+=interface_name;
				}else{
				 class_declaration+=", "+interface_name;
				}
			}
		}
				
		return class_declaration;
	}
	
	public String showClassConstructors(){
		Class<?> cls = getClass(this.className);
		String class_constructors="";
		Constructor<?>[] constructors = cls.getDeclaredConstructors();
		String modifiers="";
		String constructor_name="";
		Class<?>[] parameter_types, exception_types = null;
		
		for (int i=0; i<constructors.length;i++){
			String con_info="";
			modifiers = modifier(constructors[i].getModifiers());
			constructor_name= typeName(constructors[i].getDeclaringClass()); 
			con_info+="  " + modifiers + constructor_name+"(";
			parameter_types = constructors[i].getParameterTypes();
			exception_types = constructors[i].getExceptionTypes();
			
			for (int j = 0; j < parameter_types.length; j++) {
				if (j > 0)
					con_info+=", ";
				con_info += typeName(parameter_types[j]);
			}
			
			con_info+=")";
			
			if (exception_types.length > 0)
				con_info+=" throws ";
			for (int k = 0; k < exception_types.length; k++) {
				if (k > 0)
					con_info+=", ";
				con_info += typeName(exception_types[k]);
			}
			
			con_info+=";";
			
			class_constructors += con_info + "\n";
		}
		return class_constructors;
	}
	
	public String showClassMethods(){
		Class<?> cls = getClass(this.className);
		Method[] methods = cls.getDeclaredMethods();
		String class_methods = "";
		String modifiers = "";
		String return_type = "";
		String method_name="";
		Class<?>[] parameter_types, exception_types = null;
		
		for (int i=0; i< methods.length;i++){
			String mt_info="";
			modifiers = modifier(methods[i].getModifiers());
			return_type = typeName(methods[i].getReturnType());
			method_name = methods[i].getName();
			mt_info+="  " + modifiers + return_type+" "+method_name+"(";
			parameter_types = methods[i].getParameterTypes();
			exception_types = methods[i].getExceptionTypes();
			
					
			for (int j = 0; j < parameter_types.length; j++) {
				if (j > 0)
					mt_info+=", ";
				mt_info += typeName(parameter_types[j]);
			}
			
			mt_info+=")";
			
			if (exception_types.length > 0)
				mt_info+=" throws ";
			for (int k = 0; k < exception_types.length; k++) {
				if (k > 0)
					mt_info+=", ";
				mt_info += typeName(exception_types[k]);
			}
			
			mt_info+=";";
			
			class_methods += mt_info + "\n";
		}
		return class_methods;
	}

	public String showClassFields(){
		Class<?> cls = getClass(this.className);
		Field[] fields = cls.getDeclaredFields();
		String class_fields = "";
		String modifiers = "";
		String return_type = "";
		String field_name = "";
		
		for (Field field : fields) {
			String f="";
			modifiers = modifier(field.getModifiers());
			return_type= typeName(field.getType());
			field_name= field.getName();

			f += "  "+  modifiers + return_type + " " + field_name+ ";";
			class_fields += f + "\n";
		}
			
		return class_fields;
	}
	
	public String showEntireClass(){
		String entireClass="";
		
		entireClass+=showClassDeclaration()+"{\n"+"  // Constructors" + "\n";
		entireClass+=showClassConstructors()+"\n"+"  // Fields" + "\n";
		entireClass+=showClassFields()+"\n"+"  // Methods" + "\n";
		entireClass+=showClassMethods()+"\n";
		entireClass+="}";
		
		return entireClass;
	}
	
	private String typeName(Class<?> cls) {
		String brackets = "";
		while (cls.isArray()) { // deal with multidimensional array
			brackets += "[]";
			cls = cls.getComponentType();
		}
		String full_name = cls.getName();
		int pos = full_name.lastIndexOf('.');
		if (pos != -1)
			full_name = full_name.substring(pos + 1);
		return full_name + brackets;
	}

	private Class<?> getClass(String className) {
		Class<?> cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cls;
	}
	
	private static String modifier(int m) {
		if (m == 0)
			return "";
		else
			return Modifier.toString(m) + " ";
	}
}
