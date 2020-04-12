import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAppFileOce } from 'app/shared/model/app-file-oce.model';

type EntityResponseType = HttpResponse<IAppFileOce>;
type EntityArrayResponseType = HttpResponse<IAppFileOce[]>;

@Injectable({ providedIn: 'root' })
export class AppFileOceService {
  public resourceUrl = SERVER_API_URL + 'api/app-files';

  constructor(protected http: HttpClient) {}

  create(appFile: IAppFileOce): Observable<EntityResponseType> {
    return this.http.post<IAppFileOce>(this.resourceUrl, appFile, { observe: 'response' });
  }

  update(appFile: IAppFileOce): Observable<EntityResponseType> {
    return this.http.put<IAppFileOce>(this.resourceUrl, appFile, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAppFileOce>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAppFileOce[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
