import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { erros } from '../../messages'

@Component({
    selector: 'app-field-error-display',
    templateUrl: './field-error-display.component.html'
})
export class FieldErrorDisplayComponent {

    @Input() field: string;
    @Input() formGroup: FormGroup;

    getErrorMessage() {

        let message = "";

        const formControl = this.formGroup.get(this.field)

        if (formControl.status === "INVALID") {

            if (formControl.errors.hasOwnProperty('required')) {
                message = erros["required"]
            }
            else if (formControl.errors.hasOwnProperty('email')) {
                message = erros['email']
            }
            else if (formControl.errors.hasOwnProperty('minlength')) {
                message = erros[this.field].minLength
            }
            else if (formControl.errors.hasOwnProperty('maxlength')) {
                message = erros[this.field].maxLength
            }
            if (formControl.errors.hasOwnProperty('time')) {
                message = erros["time"]
            }
            else {
                message = erros["required"]
            }

        }

        return message;

    }

}