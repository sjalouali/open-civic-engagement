import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrganisationOce } from 'app/shared/model/organisation-oce.model';

type EntityResponseType = HttpResponse<IOrganisationOce>;
type EntityArrayResponseType = HttpResponse<IOrganisationOce[]>;

@Injectable({ providedIn: 'root' })
export class OrganisationOceService {
  public resourceUrl = SERVER_API_URL + 'api/organisations';

  constructor(protected http: HttpClient) {}

  create(organisation: IOrganisationOce): Observable<EntityResponseType> {
    return this.http.post<IOrganisationOce>(this.resourceUrl, organisation, { observe: 'response' });
  }

  update(organisation: IOrganisationOce): Observable<EntityResponseType> {
    return this.http.put<IOrganisationOce>(this.resourceUrl, organisation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrganisationOce>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrganisationOce[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
