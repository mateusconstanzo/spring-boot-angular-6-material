import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl, ValidatorFn } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';

import { AppRoutes } from '../../app-routes.enum'
import { ApiService } from '../api.service'
import { Event } from '../event'
import { BPClient } from 'blocking-proxy';

@Component({
  selector: 'app-create',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  form: FormGroup;

  numbers = [];

  btnLabel: string = "Salvar";

  edit: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private service: ApiService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {

    this.setForm();
    this.isEdit();
    this.setOptions();

  }

  back() {
    this.router.navigate([`/${AppRoutes.EVENTS}`]);
  }

  save(form: any) {

    const event = this.convertEvent(form);

    if (!this.edit) {

      this.service
        .post(event)
        .subscribe(
          (data: Event[]) => this.router.navigate([`/${AppRoutes.EVENTS}`]),
          error => console.error(error)
        );


    } else {

      this.service
        .put(event)
        .subscribe(
          (data: Event) => this.router.navigate([`/${AppRoutes.EVENTS}`]),
          error => console.error(error)
        );

    }


  }

  convertEvent(form: any): Event {

    const event = new Event();
    event.id = form.id;
    event.name = form.name;
    event.description = form.description;
    event.startDate = form.startDate.toLocaleDateString();
    event.startHour = form.startHour;
    event.endDate = form.endDate.toLocaleDateString();
    event.endHour = form.endHour;

    return event;

  }

  getById(id: number) {

    this.service.getById(id).subscribe(event => {

      this.form.setValue({
        id: event.id,
        name: event.name,
        description: event.description,
        startDate: this.formatDate(event.startDate),
        startHour: event.startHour,
        endDate: this.formatDate(event.endDate),
        endHour: event.endHour
      });

    });

  }

  isEdit() {

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {

      this.getById(
        Number.parseInt(id)
      );

      this.edit = true;
      this.btnLabel = "Editar";

    }

  }

  setForm() {

    this.form = this.formBuilder.group({
      id: ['', []],
      name: ['', [
        Validators.required,
      ]],
      description: ['', [
        Validators.required,
      ]],
      startDate: ['', [
        Validators.required,
      ]],
      startHour: ['', [
        Validators.required,
        TimeValidator()
      ]],
      endDate: ['', [
        Validators.required,
      ]],
      endHour: ['', [
        Validators.required,
        TimeValidator()
      ]]
    });

  }

  setOptions() {

    for (var i = 0; i <= 23; i++) {

      const options1 = (i > 10) ? `${i}:00` : `0${i}:00`;
      const options2 = (i > 10) ? `${i}:30` : `0${i}:30`;

      this.numbers.push(options1);
      this.numbers.push(options2)

    }
  }

  formatDate(str1: string) {
    const dt = parseInt(str1.substring(0, 2));
    const mon = parseInt(str1.substring(3, 5));
    const yr = parseInt(str1.substring(6, 10));
    return new Date(yr, mon - 1, dt);
  }

}

export function TimeValidator(): ValidatorFn {

  const pattern = /^^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/;

  return (control: AbstractControl): { [key: string]: any } => {
    return !pattern.test(control.value) ? { 'time': { value: control.value } } : null;
  };

}
