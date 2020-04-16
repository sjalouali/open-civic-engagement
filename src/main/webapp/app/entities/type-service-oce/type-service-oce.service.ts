import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeServiceOce } from 'app/shared/model/type-service-oce.model';

type EntityResponseType = HttpResponse<ITypeServiceOce>;
type EntityArrayResponseType = HttpResponse<ITypeServiceOce[]>;

@Injectable({ providedIn: 'root' })
export class TypeServiceOceService {
  public resourceUrl = SERVER_API_URL + 'api/type-services';

  constructor(protected http: HttpClient) {}

  create(typeService: ITypeServiceOce): Observable<EntityResponseType> {
    return this.http.post<ITypeServiceOce>(this.resourceUrl, typeService, { observe: 'response' });
  }

  update(typeService: ITypeServiceOce): Observable<EntityResponseType> {
    return this.http.put<ITypeServiceOce>(this.resourceUrl, typeService, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeServiceOce>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeServiceOce[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  queryPublic(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeServiceOce[]>('api/p/type-services', { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
