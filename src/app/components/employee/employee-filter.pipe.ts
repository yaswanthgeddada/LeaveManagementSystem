import { PipeTransform, Pipe } from "@angular/core";
import { Employee } from "src/app/services/employee";
@Pipe({
    name: 'employeeFilter'
})
export class EmployeeFilterPipe implements PipeTransform {
    transform(employees, searchInput: string) {
        if (!employees || !searchInput) {
            return employees;
        }
        return employees.filter(employee =>
            employee.empName.toLowerCase().indexOf(searchInput.toLowerCase()) !== -1);
    }
}