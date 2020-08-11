# Mathematical Expressions, Automatic Differentiation and Algebraic Simplification

in this project we will implement a system that can represent nested mathematical expressions that include variables, evaluate their values for specific variable assignments, differentiate them, and simplify the results.<br>

Example : 
we want to use this equation:<br>
![](https://latex.codecogs.com/svg.latex?(%202x%20+%20sin(4y)%20+%20e^x))

**to initialize :**
```java
Expression e = new Plus(new Plus(new Mul(2, "x"), new Sin(new Mul(4, "y"))), new Pow("e", "x"));
```

by override Expression's toString() method , we can print the expression : 
```java
System.out.println(e);  //(((2.0 * x) + sin((4.0 * y))) + (e^x))
```

before evaluating the expression , we must give an **assignment**to it's variables :
```java
Map<String, Double> assignment = new TreeMap<String, Double>();
assignment.put("x", 2.0);
assignment.put("y", 0.25);
assignment.put("e", 2.71);
```

then , we can **evaluate**the expression according to this assignment and get the result:
```java
double value = e.evaluate(assignment);
System.out.println("The result is: " + value); //11.361552406437283
```

we can **differentiate**the expression and **simplify**it :
```java
System.out.println(e.differentiate("x")); //((((0.0 * x) + (2.0 * 1.0)) + (cos((4.0 * y)) * ((0.0 * y) + (4.0 * 0.0)))) + ((e^x) * ((0.0 * (x / e)) + (1.0 * log(e, e)))))

System.out.println(e.differentiate("x").evaluate(assignment)); //9.344100000000001

System.out.println(e.differentiate("x").simplify()); //(2.0 + (e^x))

```

[Assignment link ](https://github.com/yoavg/ioop2018/wiki/Assignment-4)<br>

