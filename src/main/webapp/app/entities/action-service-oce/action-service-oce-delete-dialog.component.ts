import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActionServiceOce } from 'app/shared/model/action-service-oce.model';
import { ActionServiceOceService } from './action-service-oce.service';

@Component({
  templateUrl: './action-service-oce-delete-dialog.component.html'
})
export class ActionServiceOceDeleteDialogComponent {
  actionService?: IActionServiceOce;

  constructor(
    protected actionServiceService: ActionServiceOceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.actionServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('actionServiceListModification');
      this.activeModal.close();
    });
  }
}
