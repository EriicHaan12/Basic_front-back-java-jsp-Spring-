select e.empno,e.ename,e.job,e.mgr,e2.ename,e.hiredate,e.sal,e.comm,e.deptno, d.dname
from emp e , emp e2, dept d
where e.empno = e2.empno and d.deptno=e.deptno; 