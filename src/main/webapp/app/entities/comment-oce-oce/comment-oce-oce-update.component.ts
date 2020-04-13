import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ICommentOceOce, CommentOceOce } from 'app/shared/model/comment-oce-oce.model';
import { CommentOceOceService } from './comment-oce-oce.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IActionServiceOce } from 'app/shared/model/action-service-oce.model';
import { ActionServiceOceService } from 'app/entities/action-service-oce/action-service-oce.service';

type SelectableEntity = IUser | IActionServiceOce;

@Component({
  selector: 'jhi-comment-oce-oce-update',
  templateUrl: './comment-oce-oce-update.component.html'
})
export class CommentOceOceUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  actionservices: IActionServiceOce[] = [];

  editForm = this.fb.group({
    id: [],
    content: [],
    commentDate: [],
    uuidOce: [],
    uuidOrganisation: [],
    uuidEntity: [],
    archived: [],
    deleted: [],
    userId: [],
    actionServiceId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected commentOceService: CommentOceOceService,
    protected userService: UserService,
    protected actionServiceService: ActionServiceOceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commentOce }) => {
      if (!commentOce.id) {
        const today = moment().startOf('day');
        commentOce.commentDate = today;
      }

      this.updateForm(commentOce);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.actionServiceService.query().subscribe((res: HttpResponse<IActionServiceOce[]>) => (this.actionservices = res.body || []));
    });
  }

  updateForm(commentOce: ICommentOceOce): void {
    this.editForm.patchValue({
      id: commentOce.id,
      content: commentOce.content,
      commentDate: commentOce.commentDate ? commentOce.commentDate.format(DATE_TIME_FORMAT) : null,
      uuidOce: commentOce.uuidOce,
      uuidOrganisation: commentOce.uuidOrganisation,
      uuidEntity: commentOce.uuidEntity,
      archived: commentOce.archived,
      deleted: commentOce.deleted,
      userId: commentOce.userId,
      actionServiceId: commentOce.actionServiceId
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
        new JhiEventWithContent<AlertError>('openCivicEngagementApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commentOce = this.createFromForm();
    if (commentOce.id !== undefined) {
      this.subscribeToSaveResponse(this.commentOceService.update(commentOce));
    } else {
      this.subscribeToSaveResponse(this.commentOceService.create(commentOce));
    }
  }

  private createFromForm(): ICommentOceOce {
    return {
      ...new CommentOceOce(),
      id: this.editForm.get(['id'])!.value,
      content: this.editForm.get(['content'])!.value,
      commentDate: this.editForm.get(['commentDate'])!.value
        ? moment(this.editForm.get(['commentDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      uuidOce: this.editForm.get(['uuidOce'])!.value,
      uuidOrganisation: this.editForm.get(['uuidOrganisation'])!.value,
      uuidEntity: this.editForm.get(['uuidEntity'])!.value,
      archived: this.editForm.get(['archived'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      actionServiceId: this.editForm.get(['actionServiceId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommentOceOce>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
