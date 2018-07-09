import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { ApiService, ApiEvents } from '../api.service'
import { Event } from '../event'

@Component({
  selector: 'app-remove-modal',
  templateUrl: './remove-modal.component.html',
  styleUrls: ['./remove-modal.component.scss']
})
export class RemoveModalComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<RemoveModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Event,
    private service: ApiService
  ) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.closeDialog();
  }

  onOkClick(): void {
    this.service.delete(this.data.id).subscribe(res => {
      this.service.setEvent(ApiEvents.DELETED);
      this.closeDialog();
    });
  }

  private closeDialog() {
    this.dialogRef.close();
  }

}
