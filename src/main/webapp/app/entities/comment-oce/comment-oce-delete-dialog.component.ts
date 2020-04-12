import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommentOce } from 'app/shared/model/comment-oce.model';
import { CommentOceService } from './comment-oce.service';

@Component({
  templateUrl: './comment-oce-delete-dialog.component.html'
})
export class CommentOceDeleteDialogComponent {
  comment?: ICommentOce;

  constructor(protected commentService: CommentOceService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commentListModification');
      this.activeModal.close();
    });
  }
}
