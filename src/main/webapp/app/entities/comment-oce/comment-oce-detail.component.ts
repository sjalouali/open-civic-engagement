import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICommentOce } from 'app/shared/model/comment-oce.model';

@Component({
  selector: 'jhi-comment-oce-detail',
  templateUrl: './comment-oce-detail.component.html'
})
export class CommentOceDetailComponent implements OnInit {
  comment: ICommentOce | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comment }) => (this.comment = comment));
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
