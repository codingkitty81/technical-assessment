import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { FormControl,FormGroup,Validators } from '@angular/forms';
import { Employee } from '../employee';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})

export class AddEmployeeComponent implements OnInit {

  constructor(private employeeservice:EmployeeService) { }

  employee : Employee = new Employee();
  submitted = false;

  ngOnInit(): void {
    this.submitted = false;
  }

  employeeSaveForm = new FormGroup({
    firstName:new FormControl(),
    lastName:new FormControl(),
    jobTitle:new FormControl(),
    age:new FormControl(),
    startDate:new FormControl(),
    endDate:new FormControl()
  });

  saveEmployee(saveEmployee) {
    this.employee = new Employee();
    this.employee.firstName = this.FirstName.value;
    console.log(this.FirstName.value);
    this.employee.lastName = this.LastName.value;
    this.employee.jobTitle = this.JobTitle.value;
    this.employee.age = this.Age.value;
    this.employee.startDate = this.StartDate.value;
    this.employee.endDate = this.EndDate.value == '' ? null : this.EndDate.value;
    this.submitted = true;
    this.save();
  }

  save() {
    this.employeeservice.createEmployee(this.employee).subscribe(data => console.log(data), error => console.log(error));
    this.employee = new Employee();
  }

  get FirstName() {
    return this.employeeSaveForm.get('firstName');
  }

  get LastName() {
    return this.employeeSaveForm.get('lastName');
  }

  get JobTitle() {
    return this.employeeSaveForm.get('jobTitle');
  }

  get Age() {
    return this.employeeSaveForm.get('age');
  }

  get StartDate() {
    return this.employeeSaveForm.get('startDate');
  }

  get EndDate() {
    return this.employeeSaveForm.get('endDate');
  }

  addEmployeeForm() {
    this.submitted = false;
    this.employeeSaveForm.reset();
  }
}
