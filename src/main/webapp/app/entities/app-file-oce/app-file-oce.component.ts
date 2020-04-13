import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAppFileOce } from 'app/shared/model/app-file-oce.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AppFileOceService } from './app-file-oce.service';
import { AppFileOceDeleteDialogComponent } from './app-file-oce-delete-dialog.component';

@Component({
  selector: 'jhi-app-file-oce',
  templateUrl: './app-file-oce.component.html'
})
export class AppFileOceComponent implements OnInit, OnDestroy {
  appFiles: IAppFileOce[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected appFileService: AppFileOceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.appFiles = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.appFileService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IAppFileOce[]>) => this.paginateAppFiles(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.appFiles = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAppFiles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAppFileOce): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAppFiles(): void {
    this.eventSubscriber = this.eventManager.subscribe('appFileListModification', () => this.reset());
  }

  delete(appFile: IAppFileOce): void {
    const modalRef = this.modalService.open(AppFileOceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.appFile = appFile;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAppFiles(data: IAppFileOce[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.appFiles.push(data[i]);
      }
    }
  }
}
