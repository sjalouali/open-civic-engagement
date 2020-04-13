import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ITypeServiceOce, TypeServiceOce } from 'app/shared/model/type-service-oce.model';
import { TypeServiceOceService } from './type-service-oce.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-type-service-oce-update',
  templateUrl: './type-service-oce-update.component.html'
})
export class TypeServiceOceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameShort: [null, [Validators.required, Validators.minLength(3)]],
    nameLong: [],
    description: [],
    typeServiceColor: [],
    uuidOce: [],
    uuidOrganisation: [],
    uuidEntity: [],
    archived: [],
    deleted: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected typeServiceService: TypeServiceOceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeService }) => {
      this.updateForm(typeService);
    });
  }

  updateForm(typeService: ITypeServiceOce): void {
    this.editForm.patchValue({
      id: typeService.id,
      nameShort: typeService.nameShort,
      nameLong: typeService.nameLong,
      description: typeService.description,
      typeServiceColor: typeService.typeServiceColor,
      uuidOce: typeService.uuidOce,
      uuidOrganisation: typeService.uuidOrganisation,
      uuidEntity: typeService.uuidEntity,
      archived: typeService.archived,
      deleted: typeService.deleted
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
    const typeService = this.createFromForm();
    if (typeService.id !== undefined) {
      this.subscribeToSaveResponse(this.typeServiceService.update(typeService));
    } else {
      this.subscribeToSaveResponse(this.typeServiceService.create(typeService));
    }
  }

  private createFromForm(): ITypeServiceOce {
    return {
      ...new TypeServiceOce(),
      id: this.editForm.get(['id'])!.value,
      nameShort: this.editForm.get(['nameShort'])!.value,
      nameLong: this.editForm.get(['nameLong'])!.value,
      description: this.editForm.get(['description'])!.value,
      typeServiceColor: this.editForm.get(['typeServiceColor'])!.value,
      uuidOce: this.editForm.get(['uuidOce'])!.value,
      uuidOrganisation: this.editForm.get(['uuidOrganisation'])!.value,
      uuidEntity: this.editForm.get(['uuidEntity'])!.value,
      archived: this.editForm.get(['archived'])!.value,
      deleted: this.editForm.get(['deleted'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeServiceOce>>): void {
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
}
