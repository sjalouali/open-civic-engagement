import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IOrganisationOce, OrganisationOce } from 'app/shared/model/organisation-oce.model';
import { OrganisationOceService } from './organisation-oce.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-organisation-oce-update',
  templateUrl: './organisation-oce-update.component.html'
})
export class OrganisationOceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(3)]],
    description: [],
    legalId: [],
    accreditation: [],
    additionalInformation: [],
    legalStatus: [null, [Validators.required]]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected organisationService: OrganisationOceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organisation }) => {
      this.updateForm(organisation);
    });
  }

  updateForm(organisation: IOrganisationOce): void {
    this.editForm.patchValue({
      id: organisation.id,
      name: organisation.name,
      description: organisation.description,
      legalId: organisation.legalId,
      accreditation: organisation.accreditation,
      additionalInformation: organisation.additionalInformation,
      legalStatus: organisation.legalStatus
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
    const organisation = this.createFromForm();
    if (organisation.id !== undefined) {
      this.subscribeToSaveResponse(this.organisationService.update(organisation));
    } else {
      this.subscribeToSaveResponse(this.organisationService.create(organisation));
    }
  }

  private createFromForm(): IOrganisationOce {
    return {
      ...new OrganisationOce(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      legalId: this.editForm.get(['legalId'])!.value,
      accreditation: this.editForm.get(['accreditation'])!.value,
      additionalInformation: this.editForm.get(['additionalInformation'])!.value,
      legalStatus: this.editForm.get(['legalStatus'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganisationOce>>): void {
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
