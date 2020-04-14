export interface IAppFileOce {
  id?: number;
  name?: string;
  path?: string;
  fileSize?: string;
  extention?: string;
  uuidOce?: string;
  uuidOrganisation?: string;
  uuidEntity?: string;
  archived?: boolean;
  deleted?: boolean;
  commentOceId?: number;
  actionServiceId?: number;
}

export class AppFileOce implements IAppFileOce {
  constructor(
    public id?: number,
    public name?: string,
    public path?: string,
    public fileSize?: string,
    public extention?: string,
    public uuidOce?: string,
    public uuidOrganisation?: string,
    public uuidEntity?: string,
    public archived?: boolean,
    public deleted?: boolean,
    public commentOceId?: number,
    public actionServiceId?: number
  ) {
    this.archived = this.archived || false;
    this.deleted = this.deleted || false;
  }
}
