import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommentOceOce } from 'app/shared/model/comment-oce-oce.model';
import { CommentOceOceService } from './comment-oce-oce.service';

@Component({
  templateUrl: './comment-oce-oce-delete-dialog.component.html'
})
export class CommentOceOceDeleteDialogComponent {
  commentOce?: ICommentOceOce;

  constructor(
    protected commentOceService: CommentOceOceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commentOceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commentOceListModification');
      this.activeModal.close();
    });
  }
}
