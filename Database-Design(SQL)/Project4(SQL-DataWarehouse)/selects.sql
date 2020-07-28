select loanfact.time_key, loanfact.department_code, count(*) as total_loans
from loanfact, timeinfo, departments
where loanfact.time_key = timeinfo.time_key and departments.department_code = loanfact.department_code
group by loanfact.time_key, loanfact.department_code;