import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IActionServiceOce, ActionServiceOce } from 'app/shared/model/action-service-oce.model';
import { NatureService } from 'app/shared/model/enumerations/nature-service.model';
import { ActionServiceOceService } from './register-action-service-oce.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { ITypeServiceOce } from 'app/shared/model/type-service-oce.model';
import { TypeServiceOceService } from 'app/entities/type-service-oce/type-service-oce.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IOrganisationOce } from 'app/shared/model/organisation-oce.model';
import { OrganisationOceService } from 'app/entities/organisation-oce/organisation-oce.service';

type SelectableEntity = ITypeServiceOce | IUser | IOrganisationOce;

@Component({
  selector: 'jhi-register-action-service-oce-update',
  templateUrl: './register-action-service-oce-update.component.html'
})
export class ActionServiceOceUpdateComponent implements OnInit {
  isSaving = false;
  typeservices: ITypeServiceOce[] = [];
  users: IUser[] = [];
  organisations: IOrganisationOce[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(3)]],
    description: [],
    nature: [null, [Validators.required]],
    missionObjective: [],
    amount: [],
    beneficiaryNumber: [],
    volunteerNumber: [],
    additionalInformation: [],
    firstName: [null, [Validators.required, Validators.minLength(3)]],
    lastName: [null, [Validators.required, Validators.minLength(3)]],
    email: [],
    phoneNumber: [null, [Validators.required, Validators.minLength(10)]],
    startDate: [],
    endDate: [],
    labelAdresse: [],
    streetNumberAdresse: [],
    routeAdresse: [],
    locality: [],
    county: [],
    country: [],
    postalCode: [],
    latitude: [],
    longitude: [],
    uuidOce: [],
    uuidOrganisation: [],
    uuidEntity: [],
    archived: [],
    deleted: [],
    typeServiceId: [],
    users: [],
    organisationId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected actionServiceService: ActionServiceOceService,
    protected typeServiceService: TypeServiceOceService,
    protected userService: UserService,
    protected organisationService: OrganisationOceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ actionService }) => {
      if (!actionService.id) {
        const today = moment().startOf('day');
        actionService.startDate = today;
        actionService.endDate = today;
      }
      this.updateForm(actionService);

      this.typeServiceService.query().subscribe((res: HttpResponse<ITypeServiceOce[]>) => (this.typeservices = res.body || []));

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.organisationService.query().subscribe((res: HttpResponse<IOrganisationOce[]>) => (this.organisations = res.body || []));
    });

    // INIT form demande
    this.editForm.patchValue({
      name: 'test name',
      nature: NatureService.DEMANDE
    });
  }

  updateForm(actionService: IActionServiceOce): void {
    this.editForm.patchValue({
      id: actionService.id,
      name: actionService.name,
      description: actionService.description,
      nature: actionService.nature,
      missionObjective: actionService.missionObjective,
      amount: actionService.amount,
      beneficiaryNumber: actionService.beneficiaryNumber,
      volunteerNumber: actionService.volunteerNumber,
      additionalInformation: actionService.additionalInformation,
      firstName: actionService.firstName,
      lastName: actionService.lastName,
      email: actionService.email,
      phoneNumber: actionService.phoneNumber,
      startDate: actionService.startDate ? actionService.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: actionService.endDate ? actionService.endDate.format(DATE_TIME_FORMAT) : null,
      labelAdresse: actionService.labelAdresse,
      streetNumberAdresse: actionService.streetNumberAdresse,
      routeAdresse: actionService.routeAdresse,
      locality: actionService.locality,
      county: actionService.county,
      country: actionService.country,
      postalCode: actionService.postalCode,
      latitude: actionService.latitude,
      longitude: actionService.longitude,
      uuidOce: actionService.uuidOce,
      uuidOrganisation: actionService.uuidOrganisation,
      uuidEntity: actionService.uuidEntity,
      archived: actionService.archived,
      deleted: actionService.deleted,
      typeServiceId: actionService.typeServiceId,
      users: actionService.users,
      organisationId: actionService.organisationId
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
    const actionService = this.createFromForm();
    // eslint-disable-next-line no-console
    console.log(actionService);
    if (actionService.id !== undefined) {
      this.subscribeToSaveResponse(this.actionServiceService.update(actionService));
    } else {
      this.subscribeToSaveResponse(this.actionServiceService.create(actionService));
    }
  }

  private createFromForm(): IActionServiceOce {
    return {
      ...new ActionServiceOce(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      nature: this.editForm.get(['nature'])!.value,
      // missionObjective: this.editForm.get(['missionObjective'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      beneficiaryNumber: this.editForm.get(['beneficiaryNumber'])!.value,
      // volunteerNumber: this.editForm.get(['volunteerNumber'])!.value,
      // additionalInformation: this.editForm.get(['additionalInformation'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      email: this.editForm.get(['email'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      labelAdresse: this.editForm.get(['labelAdresse'])!.value,
      streetNumberAdresse: this.editForm.get(['streetNumberAdresse'])!.value,
      routeAdresse: this.editForm.get(['routeAdresse'])!.value,
      locality: this.editForm.get(['locality'])!.value,
      county: this.editForm.get(['county'])!.value,
      country: this.editForm.get(['country'])!.value,
      // postalCode: this.editForm.get(['postalCode'])!.value,
      // latitude: this.editForm.get(['latitude'])!.value,
      // longitude: this.editForm.get(['longitude'])!.value,
      // uuidOce: this.editForm.get(['uuidOce'])!.value,
      // uuidOrganisation: this.editForm.get(['uuidOrganisation'])!.value,
      // uuidEntity: this.editForm.get(['uuidEntity'])!.value,
      // archived: this.editForm.get(['archived'])!.value,
      // deleted: this.editForm.get(['deleted'])!.value,
      typeServiceId: this.editForm.get(['typeServiceId'])!.value
      // users: this.editForm.get(['users'])!.value,
      // organisationId: this.editForm.get(['organisationId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActionServiceOce>>): void {
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

  getSelected(selectedVals: IUser[], option: IUser): IUser {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }

  onSuggestion($event: any): void {
    // eslint-disable-next-line no-console
    console.log($event);
    // eslint-disable-next-line no-console
    console.log($event.suggestion.name);
    // eslint-disable-next-line no-console
    console.log($event.suggestion.postcode);
    // eslint-disable-next-line no-console
    console.log($event.suggestion.city);
    // eslint-disable-next-line no-console
    console.log($event.suggestion.county);
    // eslint-disable-next-line no-console
    console.log($event.suggestion.country);
  }

  onClear($event: any): void {
    // eslint-disable-next-line no-console
    console.log($event);
  }
}
