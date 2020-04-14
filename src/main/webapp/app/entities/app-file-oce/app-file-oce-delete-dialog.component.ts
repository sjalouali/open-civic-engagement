import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAppFileOce } from 'app/shared/model/app-file-oce.model';
import { AppFileOceService } from './app-file-oce.service';

@Component({
  templateUrl: './app-file-oce-delete-dialog.component.html'
})
export class AppFileOceDeleteDialogComponent {
  appFile?: IAppFileOce;

  constructor(protected appFileService: AppFileOceService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.appFileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('appFileListModification');
      this.activeModal.close();
    });
  }
}
