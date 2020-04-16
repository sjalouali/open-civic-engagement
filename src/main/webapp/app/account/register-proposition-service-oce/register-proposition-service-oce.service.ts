import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IActionServiceOce } from 'app/shared/model/action-service-oce.model';

type EntityResponseType = HttpResponse<IActionServiceOce>;
type EntityArrayResponseType = HttpResponse<IActionServiceOce[]>;

@Injectable({ providedIn: 'root' })
export class PropositionServiceOceService {
  public resourceUrl = SERVER_API_URL + 'api/action-services';

  constructor(protected http: HttpClient) {}

  create(actionService: IActionServiceOce): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(actionService);
    return this.http
      .post<IActionServiceOce>('api/p/action-services', copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(actionService: IActionServiceOce): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(actionService);
    return this.http
      .put<IActionServiceOce>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IActionServiceOce>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IActionServiceOce[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(actionService: IActionServiceOce): IActionServiceOce {
    const copy: IActionServiceOce = Object.assign({}, actionService, {
      startDate: actionService.startDate && actionService.startDate.isValid() ? actionService.startDate.toJSON() : undefined,
      endDate: actionService.endDate && actionService.endDate.isValid() ? actionService.endDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((actionService: IActionServiceOce) => {
        actionService.startDate = actionService.startDate ? moment(actionService.startDate) : undefined;
        actionService.endDate = actionService.endDate ? moment(actionService.endDate) : undefined;
      });
    }
    return res;
  }
}
