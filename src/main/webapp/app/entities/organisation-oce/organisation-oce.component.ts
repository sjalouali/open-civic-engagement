import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrganisationOce } from 'app/shared/model/organisation-oce.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OrganisationOceService } from './organisation-oce.service';
import { OrganisationOceDeleteDialogComponent } from './organisation-oce-delete-dialog.component';

@Component({
  selector: 'jhi-organisation-oce',
  templateUrl: './organisation-oce.component.html'
})
export class OrganisationOceComponent implements OnInit, OnDestroy {
  organisations: IOrganisationOce[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected organisationService: OrganisationOceService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.organisations = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.organisationService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IOrganisationOce[]>) => this.paginateOrganisations(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.organisations = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrganisations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrganisationOce): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInOrganisations(): void {
    this.eventSubscriber = this.eventManager.subscribe('organisationListModification', () => this.reset());
  }

  delete(organisation: IOrganisationOce): void {
    const modalRef = this.modalService.open(OrganisationOceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.organisation = organisation;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateOrganisations(data: IOrganisationOce[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.organisations.push(data[i]);
      }
    }
  }
}
