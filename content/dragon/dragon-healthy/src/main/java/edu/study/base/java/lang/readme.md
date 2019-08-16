#Introduce

#Content

##Class StringTest
Key: String intern()

###Description
This method returns a canonical representation for the string object. It follows that for any two strings s and t, s.intern() == t.intern() is true if and only if s.equals(t) is true.

###Example

In this method testForJDK17(), there is a example about running like this.

>String a = "e"; 
>String b = "f";
>String c = a + b;
>String d = a + b;
>System.out.println(c == c.intern()); //true
>System.out.println(d == d.intern()); //false
>System.out.println(c == d.intern()); //true
>System.out.println(System.identityHashCode(c)); //1618212626
>System.out.println(System.identityHashCode(d)); //1618212628
>System.out.println(System.identityHashCode(c.intern())); //1618212626
>System.out.println(System.identityHashCode(d.intern())); //1618212626
>System.out.println(System.identityHashCode("ef")); //1618212626

#Notes