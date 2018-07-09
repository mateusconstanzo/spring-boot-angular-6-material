import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar, MatDialog } from '@angular/material';

import { AppRoutes } from '../../app-routes.enum'

import { RemoveModalComponent } from '../remove-modal/remove-modal.component'

import { ApiService, ApiEvents } from '../api.service'
import { Event } from '../event'

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  events: Event[];

  constructor(
    private service: ApiService,
    private router: Router,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.listenEvents();
    this.getEvents();
  }

  getEvents() {
    this.service
      .get()
      .subscribe(
        (data: Event[]) => this.events = data,
        error => console.error(error)
      );
  }

  form() {
    this.router.navigate([`/${AppRoutes.EVENTS_NEW}`]);
  }

  edit(id: number) {
    this.router.navigate([`/${AppRoutes.EVENTS}/${id}`]);
  }

  delete(event: Event) {

    this.dialog.open(RemoveModalComponent, {
      data: event
    });

  }

  listenEvents() {
    this.service.events.subscribe(event => {
      switch (event) {
        case ApiEvents.DELETED:
          this.getEvents();
          break;
        default:
          break;
      }
    });
  }

}
