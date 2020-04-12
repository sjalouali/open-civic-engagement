import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICommentOce, CommentOce } from 'app/shared/model/comment-oce.model';
import { CommentOceService } from './comment-oce.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-comment-oce-update',
  templateUrl: './comment-oce-update.component.html'
})
export class CommentOceUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    content: [],
    commentDate: [],
    userId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected commentService: CommentOceService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comment }) => {
      if (!comment.id) {
        const today = moment().startOf('day');
        comment.commentDate = today;
      }

      this.updateForm(comment);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(comment: ICommentOce): void {
    this.editForm.patchValue({
      id: comment.id,
      content: comment.content,
      commentDate: comment.commentDate ? comment.commentDate.format(DATE_TIME_FORMAT) : null,
      userId: comment.userId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('jhipsterApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comment = this.createFromForm();
    if (comment.id !== undefined) {
      this.subscribeToSaveResponse(this.commentService.update(comment));
    } else {
      this.subscribeToSaveResponse(this.commentService.create(comment));
    }
  }

  private createFromForm(): ICommentOce {
    return {
      ...new CommentOce(),
      id: this.editForm.get(['id'])!.value,
      content: this.editForm.get(['content'])!.value,
      commentDate: this.editForm.get(['commentDate'])!.value
        ? moment(this.editForm.get(['commentDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      userId: this.editForm.get(['userId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommentOce>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
