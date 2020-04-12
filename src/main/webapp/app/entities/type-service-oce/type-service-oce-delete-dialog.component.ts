import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeServiceOce } from 'app/shared/model/type-service-oce.model';
import { TypeServiceOceService } from './type-service-oce.service';

@Component({
  templateUrl: './type-service-oce-delete-dialog.component.html'
})
export class TypeServiceOceDeleteDialogComponent {
  typeService?: ITypeServiceOce;

  constructor(
    protected typeServiceService: TypeServiceOceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeServiceListModification');
      this.activeModal.close();
    });
  }
}
