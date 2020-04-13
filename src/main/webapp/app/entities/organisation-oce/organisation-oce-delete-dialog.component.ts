import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganisationOce } from 'app/shared/model/organisation-oce.model';
import { OrganisationOceService } from './organisation-oce.service';

@Component({
  templateUrl: './organisation-oce-delete-dialog.component.html'
})
export class OrganisationOceDeleteDialogComponent {
  organisation?: IOrganisationOce;

  constructor(
    protected organisationService: OrganisationOceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.organisationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('organisationListModification');
      this.activeModal.close();
    });
  }
}
