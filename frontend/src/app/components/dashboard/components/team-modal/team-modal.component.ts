import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-team-modal',
  templateUrl: './team-modal.component.html',
  styleUrls: ['./team-modal.component.scss']
})
export class TeamModalComponent {
 @Input() props! : ModalProps;
 @Output() saveClicked = new EventEmitter<void>();
  save() {
      this.saveClicked.emit();
  }

  cancel() {
    this.props.title = ''
    this.props.show = !this.props.show;
  }
}

export interface ModalProps {
  title: string;
  mode: 'add' | 'update';
  data?: any;
  show? : boolean;
  task? :string;
}
