import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAppFileOce, AppFileOce } from 'app/shared/model/app-file-oce.model';
import { AppFileOceService } from './app-file-oce.service';
import { ICommentOce } from 'app/shared/model/comment-oce.model';
import { CommentOceService } from 'app/entities/comment-oce/comment-oce.service';
import { IActionServiceOce } from 'app/shared/model/action-service-oce.model';
import { ActionServiceOceService } from 'app/entities/action-service-oce/action-service-oce.service';

type SelectableEntity = ICommentOce | IActionServiceOce;

@Component({
  selector: 'jhi-app-file-oce-update',
  templateUrl: './app-file-oce-update.component.html'
})
export class AppFileOceUpdateComponent implements OnInit {
  isSaving = false;
  comments: ICommentOce[] = [];
  actionservices: IActionServiceOce[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(3)]],
    path: [],
    fileSize: [],
    extention: [],
    commentId: [],
    actionServiceId: []
  });

  constructor(
    protected appFileService: AppFileOceService,
    protected commentService: CommentOceService,
    protected actionServiceService: ActionServiceOceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appFile }) => {
      this.updateForm(appFile);

      this.commentService.query().subscribe((res: HttpResponse<ICommentOce[]>) => (this.comments = res.body || []));

      this.actionServiceService.query().subscribe((res: HttpResponse<IActionServiceOce[]>) => (this.actionservices = res.body || []));
    });
  }

  updateForm(appFile: IAppFileOce): void {
    this.editForm.patchValue({
      id: appFile.id,
      name: appFile.name,
      path: appFile.path,
      fileSize: appFile.fileSize,
      extention: appFile.extention,
      commentId: appFile.commentId,
      actionServiceId: appFile.actionServiceId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appFile = this.createFromForm();
    if (appFile.id !== undefined) {
      this.subscribeToSaveResponse(this.appFileService.update(appFile));
    } else {
      this.subscribeToSaveResponse(this.appFileService.create(appFile));
    }
  }

  private createFromForm(): IAppFileOce {
    return {
      ...new AppFileOce(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      path: this.editForm.get(['path'])!.value,
      fileSize: this.editForm.get(['fileSize'])!.value,
      extention: this.editForm.get(['extention'])!.value,
      commentId: this.editForm.get(['commentId'])!.value,
      actionServiceId: this.editForm.get(['actionServiceId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppFileOce>>): void {
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
