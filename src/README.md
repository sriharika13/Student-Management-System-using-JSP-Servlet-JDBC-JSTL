1. created login.js- login page
2. when logged out, and clicked on back button u get secured pages- 
because pages are cached by browser, can be solved by setting headers using res obj
3. session attribute is browser specific. If i logged in from safari(uname is stored in session of safari browser) then 
i won't be able to view secured pages from firefox. 
4. If there is no such catch block, then the exception will ultimately be caught by the 
default exception handler of the runtime environment, which will usually print a stack trace and terminate the program.
5. When post is used, parameters are sent in the request body and when get method is used params sent through url
6. <a href="edit?id=<c:out value='${row.id}' />">  
            <button>Edit</button>
        </a> means doGet must be used in ListServlet