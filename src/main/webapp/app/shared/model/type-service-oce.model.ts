export interface ITypeServiceOce {
  id?: number;
  nameShort?: string;
  nameLong?: string;
  description?: any;
  typeServiceColor?: string;
  uuidOce?: string;
  uuidOrganisation?: string;
  uuidEntity?: string;
  archived?: boolean;
  deleted?: boolean;
}

export class TypeServiceOce implements ITypeServiceOce {
  constructor(
    public id?: number,
    public nameShort?: string,
    public nameLong?: string,
    public description?: any,
    public typeServiceColor?: string,
    public uuidOce?: string,
    public uuidOrganisation?: string,
    public uuidEntity?: string,
    public archived?: boolean,
    public deleted?: boolean
  ) {
    this.archived = this.archived || false;
    this.deleted = this.deleted || false;
  }
}
