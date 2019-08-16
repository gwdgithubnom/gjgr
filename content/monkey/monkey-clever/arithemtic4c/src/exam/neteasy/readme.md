# Introudce

## GausssCircleProblem
Count the number of lattice points N(r) inside the boundary of a circle of radius r with center at the origin. The exact solution is given by the sum

N(r)	=	1+4|_r_|+4sum_(i=1)^(|_r_|)|_sqrt(r^2-i^2)_|	
(1)
	=	1+4sum_(i=1)^(r^2)(-1)^(i-1)|_(r^2)/(2i-1)_|	
(2)
	=	1+4sum_(i=0)^(infty)(|_(r^2)/(4i+1)_|-|_(r^2)/(4i+3)_|)	
(3)
(Hilbert and Cohn-Vossen 1999, p. 39). The first few values for r=0, 1, ... are 1, 5, 13, 29, 49, 81, 113, 149, ... (OEIS A000328).

The series for N(r) is intimately connected with the sum of squares function r(n) (i.e., the number of representations of n by two squares), since

 N(r)=sum_(n=0)^(r^2)r(n) 	
(4)
(Hardy 1999, p. 67). N(r) is also closely connected with the Leibniz series since

 1/4[(N(r))/(r^2)-1/(r^2)]=1-1/3+1/5-1/7+...+/-1/r+/-(E(r))/r 
=1/4[pi+2Phi(-1,1,1/2+r)]+/-(E(r))/r 
=1/4[pi+psi_0(1/4(3+2r))-psi_0(1/4(1+2r))]+/-(E(r))/r,  	
(5)
where Phi(z,s,a) is a Lerch transcendent and psi_0(x) is a digamma function, so taking the limit r->infty gives

 1/4pi=1-1/3+1/5-1/7+1/9+... 	
(6)
(Hilbert and Cohn-Vossen 1999, p. 39).

Gauss showed that

 N(r)=pir^2+E(r), 	
(7)
where

 |E(r)|<=2sqrt(2)pir 	
(8)
(Hardy 1999, p. 67).

GausssCirclePi
The first few values of N(r)/r^2 are 5, 13/4, 29/9, 49/16, 81/25, 113/36, 149/49, 197/64, 253/81, 317/100, 377/121, 49/16, ... (OEIS A000328 and A093837). As can be seen in the plot above, the values of r such that N(r)/r^2>pi are r=2, 3, 4, 6, 11, 16, 21, 36, 52, 53, 86, 101, ... (OEIS A093832).

Writing |E(r)|<=Cr^theta, the best bounds on  theta are

 1/2<theta<=131/208 approx 0.62981 	
(9)
(Huxley 2003). The lower limit 1/2 was obtained independently by Hardy and Landau in 1915. The following table summarizes incremental improvements in the upper limit (updated from Hardy 1999, p. 81).

theta	approx.	citation
1	1.00000	Dirichlet
2/3	0.66667	Voronoi (1903), Sierpiński (1906), van der Corput (1923)
37/56	0.66071	Littlewood and Walfisz (1925)
33/50	0.66000	van der Corput (1922)
27/41	0.65854	van der Corput (1928)
15/23	0.65217	
24/37	0.64865	Chen (1963), Kolesnik (1969)
35/54	0.64815	Kolesnik (1982)
278/429	0.64802	Kolesnik
34/53	0.64151	Vinogradov (1935)
7/11	0.63636	Iwaniec and Mozzochi (1988)
46/73	0.63014	Huxley (1993)
131/208	0.62981	Huxley (2003)
The problem has also been extended to conics, ellipsoids (Hardy 1915), and higher dimensions.

# Notes
## Reference
[http://mathworld.wolfram.com/GausssCircleProblem.html](http://mathworld.wolfram.com/GausssCircleProblem.html)
