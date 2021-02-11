import { PipeTransform, Pipe } from "@angular/core";
import { Employee } from "src/app/services/employee";
@Pipe({
    name: 'leaveFilter'
})
export class LeaveFilterPipe implements PipeTransform {
    transform(managerEmployee, searchInput: string) {
        if (!managerEmployee || !searchInput) {
            return managerEmployee;
        }
        return managerEmployee.filter(employee =>
            employee.appliedOn.toLowerCase().indexOf(searchInput.toLowerCase()) !== -1);
    }
}