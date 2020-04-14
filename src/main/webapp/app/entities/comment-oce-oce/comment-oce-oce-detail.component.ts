import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICommentOceOce } from 'app/shared/model/comment-oce-oce.model';

@Component({
  selector: 'jhi-comment-oce-oce-detail',
  templateUrl: './comment-oce-oce-detail.component.html'
})
export class CommentOceOceDetailComponent implements OnInit {
  commentOce: ICommentOceOce | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commentOce }) => (this.commentOce = commentOce));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
